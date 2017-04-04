package com.pikup.pash.pikup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by since on 4/3/2017.
 */

public class FilterCategoryActivity extends AppCompatActivity
{
    private Button nextPageButton;
    public String category;
    public static final String KEY_CATEGORY="category";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_category);

        nextPageButton = (Button) findViewById(R.id.btnNextPage);

        nextPageButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(FilterCategoryActivity.this, ViewActivity.class);
                intent.putExtra(KEY_CATEGORY, getCategory());
                startActivity(intent);
            }
        });
    }
    public void selectCategory (View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId())
        {
            case R.id.electronic_Cat:
                if (checked)
                {
                    category = "electronics";
                }
                break;
            case R.id.furniture_Cat:
                if (checked)
                {
                    category = "furniture";
                }
                break;
            case R.id.appliance_Cat:
                if (checked)
                {
                    category = "appliances";
                }
                break;
            case R.id.misc_Cat:
                if (checked)
                {
                    category = "miscellaneous";
                }
                break;

        }//end switch

    }//end selectCategory

    public String getCategory()
    {
        return category;
    }

}
