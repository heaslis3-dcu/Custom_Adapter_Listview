/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.flavor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by seanh on 27/11/2017.
 * Refactored main Activity for consistency.
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


}
