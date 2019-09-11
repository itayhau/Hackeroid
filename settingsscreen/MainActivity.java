package com.example.itaykan.settingsscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String MY_FILE_NAME = "MyFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySettings();
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        // if you click the ACTION text...
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                displaySettings();
                            }
                        }).show();
                        */
            }
        });
        findViewById(R.id.settingsTV).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displaySettings();
            }
        });

        findViewById(R.id.loadBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences(MY_FILE_NAME, MODE_PRIVATE);
                String name = prefs.getString("name", "");//"No name defined" is the default value.
                String msg = "Loaded";
                if (name.equals("")) {
                    msg = "Nothing to load";
                }
                else
                {
                    EditText et = findViewById(R.id.nameET);
                    et.setText(name);
                }
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MY_PREFS_NAME - a static String variable like:
                //public static final String MY_PREFS_NAME = "MyPrefsFile";
                SharedPreferences.Editor editor = getSharedPreferences(MY_FILE_NAME, MODE_PRIVATE).edit();
                EditText et = findViewById(R.id.nameET);
                editor.putString("name", et.getText().toString());
                editor.apply();
                Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void displaySettings()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String username = prefs.getString("username", "Default NickName");
        boolean updates = prefs.getBoolean("applicationUpdates", false);
        String st = prefs.getString("downloadDetials", "1");
        int downloadDetialsNumber = 1;
        try {
            //int downloadDetialsNumber = prefs.getInt("downloadDetials", 1);
            downloadDetialsNumber = Integer.parseInt(st);
        }
        catch (Exception ex)
        {
            Toast.makeText(getBaseContext(), "Cannot convert " + st + " to int", Toast.LENGTH_SHORT).show();
        }


        StringBuilder builder = new StringBuilder();
        builder.append("Username: " + username + "\n");
        builder.append("Updates: " + updates+ "\n");
        builder.append("Download details: " + getResources().getStringArray(R.array.listArray)[downloadDetialsNumber - 1]);
        TextView sttv = findViewById(R.id.settingsTV);
        sttv.setText(builder.toString());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent in = new Intent(this, MyPreferencesActivity.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
