/**
 * Authors Daniel Garcia,Gurdev Mann,Harbandan Sadana
 * Version 1.0
 * ToDoList Android Application
 * TodoList.apk
 */


package com.example.dev.proj3gui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    /**
     * Main Class Contains Following
     * ArrayList to Hold the items
     * EditText to get input
     * Remove Button To Remove An Item from the List
     * Add To Add an Item
     * Save Button To Save the list
     */
    ArrayList<String> mainArray;
    ItemsArrayAdapter itemsArrayAdapter;
    EditText newItemTxt;
    ListView itemsListView;
    Button removeItemBtn;
    Button saveItemBtn;


    @Override
    /**
     * Start the App And Lauch the Activity_main.xml
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainArray = new ArrayList<>();
        /**
         * Set all new components to point to the buttoms created on the layout
         * So changes can happen when they are clicked on
         */
        itemsArrayAdapter = new ItemsArrayAdapter(this, mainArray);
        itemsListView = (ListView) findViewById(R.id.newItemsView);
        removeItemBtn = (Button) findViewById(R.id.removeItemBtn);

        itemsListView.setAdapter(itemsArrayAdapter);
        itemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            view.setSelected(true);

            }
        });
        /**
         * Even Handler For Once the Button is clicked
         *
         */
        removeItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = itemsListView.getCheckedItemPosition();

                if (position > -1) {
                    // removing from adapter
                    try {
                        itemsArrayAdapter.remove(mainArray.get(position).toString());
                        itemsListView.clearChoices();
                    } catch (Exception e) {

                    }

                    Toast.makeText(getApplicationContext(), "Deleted ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No Items Selected", Toast.LENGTH_SHORT).show();
                }

                // Refresh
                itemsArrayAdapter.notifyDataSetChanged();
            }
        });

        try{
            Log.i("ON CREATE", "The on create has occured.");

           Scanner scanner = new Scanner(openFileInput("Checklist.txt"));

            while(scanner.hasNextLine()){
                String toDo = scanner.nextLine();
                itemsArrayAdapter.add(toDo);
            }

            scanner.close();
        }catch(Exception e){
            Log.i("ON CREATE", e.getMessage());
        }

    }

    /**
     * The Activity once the alert button is clicked it will Open a new activity
     * With New Layout For the Alerts
     * @param view
     */
    public void switcher(View view) {

        Intent intent = new Intent(MainActivity.this,Alerts.class);
        startActivity(intent);
    }

    /**
     * This Function adds an Item to the List
     * @param view
     */
    public void addItem(View view) {

        newItemTxt = (EditText) findViewById(R.id.newItemText);
        String newItem = newItemTxt.getText().toString();
        /**
         * If the Entry Space is empty Ask User to enter A Item
         */
        if(newItem.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please Enter Text", Toast.LENGTH_SHORT).show();
        }
        /**
         * Else add the item to the List
         */
        else {
            mainArray.add(newItem);
            Toast.makeText(getApplicationContext(),newItem + " Added to the List",Toast.LENGTH_SHORT).show();
            itemsArrayAdapter.notifyDataSetChanged();
            newItemTxt.setText("");
        }


    }

    public void saver(View view) {
        try{

            PrintWriter pw = new PrintWriter(openFileOutput("Checklist.txt", Context.MODE_PRIVATE));
            Toast.makeText(getApplicationContext(), "Saved ", Toast.LENGTH_SHORT).show();

            for(String toDo : mainArray) {
                pw.println(toDo);
            }
            pw.close();
        }catch(Exception e){

        }

    }
}
