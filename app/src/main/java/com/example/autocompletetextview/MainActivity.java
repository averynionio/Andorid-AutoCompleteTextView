package com.example.autocompletetextview;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends Activity {
    // Step 1: Define the list
    private static String[] androidBooks = new String[]{
                    "Hello, Android - Ed Burnette",
                    "Professional Android 2 App Dev - Reto Meier",
                    "Unlocking Android - Frank Ableson",
                    "Android App Development - Blake Meike",
                    "Pro Android 2 - Dave MacLean",
                    "Beginning Android 2 - Mark Murphy",
                    "Android Programming Tutorials - Mark Murphy",
                    "Android Wireless App Development - Lauren Darcey",
                    "Pro Android Games - Vladimir Silva",
    };

    ArrayList<String> list_androidBooks =
            new ArrayList<String>(Arrays.asList(androidBooks));

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Step 2: Create an ArrayAdapter from the list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, androidBooks);

        // Step 3: Create an AutoCompleteTextView.
        AutoCompleteTextView acTextView = (AutoCompleteTextView)findViewById(R.id.AndroidBooks);

        // Step 4: Bind the ArrayAdapter with the TextView.
        acTextView.setAdapter(adapter);

        // Step 5: Set To-do List
        ListView myListView = (ListView)findViewById(R.id.myListView);
        final ArrayList<String> todoItems = new ArrayList<String>();
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                todoItems);
        myListView.setAdapter(aa);

        // Step 6: Set On Key Listener
        acTextView.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                        String new_input = acTextView.getText().toString();

                        todoItems.add(0, new_input);
                        aa.notifyDataSetChanged();

                        if (!list_androidBooks.contains(new_input)) {
                            acTextView.setText(null);
                            list_androidBooks.add(new_input);
                            adapter.add(new_input);
                            acTextView.setAdapter(adapter);
                        }
                        return true;
                }
                return false;
            }
        });
    }
}

