package com.example.android.flavor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by seanh on 27/11/2017.
 */

public class MainActivity extends AppCompatActivity
{
    static private final int GET_CHEMIST_LIST_REQUEST_CODE = 1;
    static private final String TAG = "assign 3";

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     *Intent -  open Chemist View
     *
     * Start activity
     */
    public void openChemistView(View arg0)
    {
        Intent myIntent = new Intent(MainActivity.this, ChemistView.class);
        //log message
        Log.d(TAG, "Chemist list view has opened!");
        startActivity(myIntent);

    }

    /**
     *Intent -  open Perscription View
     *
     * Start activity
     */
    public void openPrescriptionView(View arg0)
    {
        Intent myIntent = new Intent(MainActivity.this, PrescriptionOrder.class);
        //log message
        Log.d(TAG, "Chemist list view has opened!");
        startActivity(myIntent);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == GET_CHEMIST_LIST_REQUEST_CODE);
        {
            // Toast.makeText(this, "Image taken", Toast.LENGTH_LONG).show();

        }
    }
}
