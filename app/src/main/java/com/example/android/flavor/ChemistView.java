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


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * {@link ChemistView} shows a list of Android platform releases.
 * For each release, display the name, version number, and image.
 */
public class ChemistView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chemist_list_activ_main);

        // Create an ArrayList of AndroidFlavor objects
        final ArrayList<AndroidFlavor> androidFlavors = new ArrayList<AndroidFlavor>();
        androidFlavors.add(new AndroidFlavor("Cosmetics", "From €1.00!", R.drawable.cosmetics));
        androidFlavors.add(new AndroidFlavor("Candles", "Candles on discount at 50% off!", R.drawable.candles));
        androidFlavors.add(new AndroidFlavor("Mens", "Enjoy a selection of mens products.", R.drawable.mens));
        androidFlavors.add(new AndroidFlavor("Lipstick", "Lipsticks on sale 25% off!", R.drawable.lipstick));
        androidFlavors.add(new AndroidFlavor("Honeycomb", "3.0-3.2.6", R.drawable.honeycomb));
        androidFlavors.add(new AndroidFlavor("Ice Cream Sandwich", "4.0-4.0.4", R.drawable.icecream));
        androidFlavors.add(new AndroidFlavor("Jelly Bean", "4.1-4.3.1", R.drawable.jellybean));
        androidFlavors.add(new AndroidFlavor("KitKat", "4.4-4.4.4", R.drawable.kitkat));
        androidFlavors.add(new AndroidFlavor("Lollipop", "5.0-5.1.1", R.drawable.lollipop));
        androidFlavors.add(new AndroidFlavor("Marshmallow", "6.0-6.0.1", R.drawable.marshmallow));

        // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
        // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
        // in the list.
        AndroidFlavorAdapter flavorAdapter = new AndroidFlavorAdapter(this, androidFlavors);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = (ListView) findViewById(R.id.listview_flavor);
        listView.setAdapter(flavorAdapter);

        /*
    * Date 30/11/2017
    * Citation - code modified from:
    * http://www.vogella.com/tutorials/AndroidListView/article.html#exercise_listsactivity_simple
    * Section 2.6 Listener,
    * Section 17. Tutorial: Miscellaneous
    */
   /* sets listener
    * Which re
    *
    * Does not require clickable or
    */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                String item = androidFlavors.get(position).getVersionName();
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
