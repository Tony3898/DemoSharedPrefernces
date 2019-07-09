package com.android.tony.demosharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    Button showDetails;
    TextView details;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        showDetails = findViewById(R.id.ShowDetails);
        details = findViewById(R.id.detailstextview);

        showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String namemsg="";
                    ArrayList<String> newUserNameList = new ArrayList<>();
                    newUserNameList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("username",ObjectSerializer.serialize(new ArrayList<String>())));
                    for (String name:newUserNameList)
                    {
                        namemsg = name + "\n" ;
                    }
                    details.setText(newUserNameList.toString());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
