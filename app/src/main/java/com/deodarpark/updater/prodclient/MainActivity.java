package com.deodarpark.updater.prodclient;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        final TextView textView = (TextView) findViewById(R.id.textView);

        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("You Clicked");
            }
        });

*/
        String[] orders = { "Order 1", "Order 2", "Order 3", "Order 4" };
        ListAdapter orderListAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, orders);

        ListView orderListView = (ListView) findViewById(R.id.orderListView);
        orderListView.setAdapter(orderListAdapter);
        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = "You selected " + String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                // switching to production entries master/details view
                Intent productionEntryListScreenIntent = new Intent(MainActivity.this, ProdEntryListActivity.class);
                productionEntryListScreenIntent.putExtra("callingActivity", "MainActivity");
                // final int result = 1;
                startActivity(productionEntryListScreenIntent);
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
