package com.example.callsms;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] numbers = {"9495434706", "9495434706", "9495434706"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numbers);

        listView = findViewById(R.id.lv);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        // Set a long click listener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openContextMenu(view);
            }
        });
    }





    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        String number = numbers[info.position];

        if (item.getItemId() == R.id.action_call) {
            // Create an intent to make a call
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
            startActivity(callIntent);
            return true;
        } else if (item.getItemId() == R.id.action_sms) {
            // Create an intent to send an SMS
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + number));
            startActivity(smsIntent);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }
}
