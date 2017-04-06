package com.pikup.pash.pikup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FilterByLocation extends AppCompatActivity {
    private EditText editText;
    //private TextView textView;
    private Button buttonOk;
    public String location;
    private Toolbar toolbar;
    public static final String KEY_LOCATION="location";

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.homemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.menu_home)
        {
            startActivity(new Intent(FilterByLocation.this, HomeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_by_location);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        buttonOk = (Button) findViewById(R.id.locSubmit);
        editText = (EditText)findViewById(R.id.locEditText);
        location = editText.getText().toString();

        buttonOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (null != getLocation())
                {
                    Intent intent = new Intent(FilterByLocation.this, ViewActivity.class);
                    intent.putExtra(KEY_LOCATION, getLocation());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enter zip code", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_by_location);


        button = (Button) findViewById(R.id.locSubmit);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    Intent intent = new Intent(FilterByLocation.this, ViewActivity.class);
                    intent.putExtra(KEY_LOCATION, getLocation());
                    startActivity(intent);
            }
        });

    }*/
    public String getLocation()
    {
        return location;
    }

}
