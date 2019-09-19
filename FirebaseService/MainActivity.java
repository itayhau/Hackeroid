package com.example.itaykan.fbfb4;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();

    ArrayAdapter adapter;
    ListView lv;

    private int counter = 0;

    private void showToken()
    {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("================", "Refreshed token: " + refreshedToken);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edittext = findViewById(R.id.msg);
        edittext.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(getBaseContext(), edittext.getText(), Toast.LENGTH_SHORT).show();

                    //mDatabaseReference = mDatabase.getReference().child("messages");
                    //mDatabase.getReference().child("messages" + (++counter));
                    Message u = new Message("me", edittext.getText().toString());
                    mDatabase.getReference().child("messages").push().setValue(u);

                    return true;
                }
                return false;
            }
        });

        //showToken();

        mDatabase.getReference().child("messages").removeValue();

        //mDatabase.getReference().child("users/1").addListenerForSingleValueEvent();
        mDatabase.getReference().child("messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                StringBuilder sb = new StringBuilder();
                int index = 0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Message message = postSnapshot.getValue(Message.class);
                    sb.append( message.getSender() + " : " + String.valueOf(message.getMsg()) + "\n");

                    if (index >= adapter.getCount())
                        adapter.add(message.getSender() + " : " + String.valueOf(message.getMsg()));
                    index++;
                }
                //lv.setSelection(adapter.getCount() - 1);
                Toast.makeText(getBaseContext(),sb ,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                int x= 1;
                Toast.makeText(getBaseContext(), "User: " + error,
                        Toast.LENGTH_SHORT).show();
            }
        });


        //Toast.makeText(this, mDatabaseReference.toString(), Toast.LENGTH_SHORT).show();

        //mDatabaseReference = mDatabase.getReference().child("users/1");
        //User u = new User("itay", 18);
        //mDatabaseReference.setValue(u);


        adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,

                new ArrayList<String>());

        lv = findViewById(R.id.dataList);
        lv.setAdapter(adapter);
    }
}
