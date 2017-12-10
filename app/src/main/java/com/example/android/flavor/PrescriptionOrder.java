/*
* * Copyright 2013 The Android Open Source Project  *
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0  *
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.flavor;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;

/**
 * Created by seanh on 30/11/2017.
 * 04/12/2017
 * https://software.intel.com/en-us/blogs/2014/03/25/capturing-handling-displaying-and-sharing-images-in-an-android-app
 */

public class PrescriptionOrder extends AppCompatActivity
{
    private static String emailTo, emailSubject, emailName, emailMsgPart1, emailMsgPart2, emailRegards;
    private static final String TAG = "Assign3";
    private static final int REQUEST_SHARE = 39714;
    private static final int REQUSET_EMAIL = 1;
    private File m_ImageFile;
    private Uri m_ImageUri;
    private String imageFileName = "myImage.jpg";
    private Spinner m_SpinnerClick;
    private TextView mSpinnerTxtV;
    private EditText edtText_Name, edtTxt_OtherInstruc, edtPhoneNum;

    /*
Citation:
Regarding Spinner Code saved in onCreate Method adapted from:
https://www.youtube.com/watch?v=aApS2W-j8oM (Slidenerd video 104 & 105)
Between 27/11/2017 and 11/12/2017
*/

    /**
     * onCreate method
     * initialises the various EditText views and code for the Spinner Adapter,
     * with setOnItemSelectedLister
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescription);
        edtText_Name = (EditText) findViewById(R.id.edtTextFrom);
        edtTxt_OtherInstruc = (EditText) findViewById(R.id.edTxtDescription);
        edtPhoneNum = (EditText) findViewById(R.id.edtPhoneNum);
        //NOT required for retrieving data from input of userName
        //m_Message = (EditText) findViewById(R.id.edTxtDescription);
        //Initialising Strings from resource - To email address
        emailTo = getResources().getString(R.string.ToEmail);
        emailSubject = getResources().getString(R.string.Msg_Subject); //Subject
        emailName = getResources().getString(R.string.Msg_Name); //Message details
        emailMsgPart1 = getResources().getString(R.string.Msg_Part1); //Message details
        emailMsgPart2 = getResources().getString(R.string.Msg_Part2); //Message details
        emailRegards = getResources().getString(R.string.Msg_Part3); //Message details
        //Spinner - selection Array Adapater
        m_SpinnerClick = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.collectionTimes,
                android.R.layout.simple_spinner_dropdown_item);
        m_SpinnerClick.setAdapter(adapter);
        //Spinner setting on item selected listener, have tested this code both inside onCreate and outside onCreate.
        // Both worked fine but could use guidance on which is best practice.
        m_SpinnerClick.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            //Implement OnItemSelectedListener Methods
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                mSpinnerTxtV = (TextView) view;

                // m_SpinnerStr = mSpinnerTxtV.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //Not required for this app
            }
        });
    }

    /**
     * Citation:
     * Between 27/11/2017 and 11/12/2017
     * Licence include above
     * MediaStore reference: Constants: ACTION_IMAGE_CAPTURE
     * https://developer.android.com/reference/android/provider/MediaStore.html
     * https://developer.android.com/reference/android/provider/MediaStore.html#ACTION_IMAGE_CAPTURE
     * Method used to open Camera and capture image.
     *
     * @param view
     */

    public void captureImage(View view)
    {
        Log.i(TAG, "Camera has been activated.");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        m_ImageFile = new File(Environment.getExternalStorageDirectory(), imageFileName);
        m_ImageUri = Uri.fromFile(m_ImageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, m_ImageUri);
        startActivityForResult(intent, REQUEST_SHARE);//note number of request_share
    }

    /**
     * Entering onActivityResult
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        Log.i(TAG, "Entering onActivityResult()");
        //Checking request for message:
        if (requestCode == REQUEST_SHARE)
        {
            if (resultCode == RESULT_OK)
            {
                Log.i(TAG, "Image saved to filepath.");
                //Toast.makeText(this, "ImageFilePath is: " + m_ImageFile, Toast.LENGTH_LONG).show();
            } else
            {
                Toast.makeText(this, "There was an error saving your accessing your image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Method checks that name and time are entered and the prescription receipt is taken
     * prior to allowing open Email intent proceeding. This is the minimum required information.
     *
     * @param view // open Intent email
     */
    /*
        code adapted from method "process"
        Found on YouTube - SlideNerd Android Tutorials - Videos 29/30
        https://www.youtube.com/watch?v=nj-STGrL7Zc&index=29&list=PLonJJ3BVjZW6hYgvtkaWvwAVvOFB7fkLa

     */
    public void openEmail(View view)
    {
        //If statement to prevent user opening email without first entering a name
        if (edtText_Name.getText().toString().isEmpty())
        {
            Toast.makeText(this, "You must enter your NAME to proceed!", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Name not entered.");
        } else if (m_ImageUri == null)
        {
            Toast.makeText(this, "The prescription photo has not been taken!", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Image not taken.");
        } else if (mSpinnerTxtV.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Oops! You forgot the collection time!", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Collection Time not selected.");
        } else
        {
            Log.i(TAG, "Entering Intent to start email activity.");
            //Procced with Intent to open email and send details including image, From and time
            Intent openEmailIntent = new Intent(Intent.ACTION_SEND);
            openEmailIntent.setType("image/*"); // lets app know image will be attached
            //Sunject Line from EditText - edtText_Subject
            openEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, emailSubject);
            //Email TO: field populated by String Resource
            openEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailTo});
            //openEmailIntent.setType("text/html");
            openEmailIntent.putExtra(Intent.EXTRA_TEXT,
                    emailName + " " + edtText_Name.getText().toString() +
                            "\n\n" + emailMsgPart1 + " " +
                            mSpinnerTxtV.getText().toString() +
                            " " + emailMsgPart2 +
                            " " + edtTxt_OtherInstruc.getText().toString() +
                            "\n\n" + emailRegards +
                            "\n\n " + edtText_Name.getText().toString() +
                            "\n\n" + edtPhoneNum.getText().toString()
            );
            openEmailIntent.putExtra(Intent.EXTRA_STREAM, m_ImageUri);
            //MIME allowing exchange of different types of data through email
            openEmailIntent.setType("message/rfc822");
            //Start Activity - open chooser to send email
            startActivity(openEmailIntent.createChooser(openEmailIntent, "Send Email"));
        }
    }
}
