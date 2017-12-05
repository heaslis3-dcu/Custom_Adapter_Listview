package com.example.android.flavor;


import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;

import java.util.ArrayList;

import java.util.List;



/**
 * Created by seanh on 30/11/2017.
 * 04/12/2017
 * https://software.intel.com/en-us/blogs/2014/03/25/capturing-handling-displaying-and-sharing-images-in-an-android-app
 */

public class PrescriptionOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    EditText m_Name;
    EditText m_Message;
    private static final String TAG = "Assign3";
    Uri m_ouputFileUri;
    private static final int REQUEST_SHARE =39714;
    private File m_ImageFile;
    private Uri m_ImageUri;
    String imageFileName = "myImage.jpg";
    String m_Spinner;
    Spinner m_SpinnerClick;
    Object m_Item;
    String m_Time;
    TextView mSpinnerText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescription);
        m_Name = (EditText) findViewById(R.id.edtTextTo);
        m_Message = (EditText) findViewById(R.id.edTxtDescription);


        m_SpinnerClick = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.collectionTimes, android.R.layout.simple_spinner_item);
        m_SpinnerClick.setAdapter(adapter);
        m_SpinnerClick.setOnItemSelectedListener(this);
    }

    /*
    Citation: https://www.youtube.com/watch?v=aApS2W-j8oM (Slidenerd video 104 & 105)
    Spinner Steps:
    - Create the data source,
    define the appearance layoutfile through which the adapter will put data inside the spinner
    define what to do when the user clicks on the spinner using the OnItemSelectedListener
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        mSpinnerText = (TextView) view;
        //Toast.makeText(this, "Selected: " + spinnerText.getText(), Toast.LENGTH_SHORT).show();
        //return spinnerText;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }


   public void captureImage(View view)
   {
       Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

       m_ImageFile = new File(Environment.getExternalStorageDirectory(),imageFileName);
       m_ImageUri =  Uri.fromFile(m_ImageFile);

       intent.putExtra(MediaStore.EXTRA_OUTPUT, m_ImageUri);
       Log.i(TAG, "Camera has taken image.");
       startActivityForResult(intent, REQUEST_SHARE);//note number of request_share
   }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i(TAG, "Entering onActivityResult()");
        //Checking request for message:
        if(requestCode == REQUEST_SHARE) {
            if(resultCode == RESULT_OK) {
                Log.i(TAG, "Image saved to filepath.");
                Toast.makeText(this, "ImageFilePath is: " + m_ImageFile, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "There was an error saving your accessing your image", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Citation - Code modified from:
     * https://www.mkyong.com/android/android-spinner-drop-down-list-example/

// adds items to spinner dynamically
public void setHoursToSpinner() {
    //Spinner
    Spinner spinner = (Spinner) findViewById(R.id.spinner);
    //Array used to hold Spinner data
    List<String> hour = new ArrayList<String>();
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, hour);
    Log.i(TAG, "Spinner activated.");
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
}
     */
    /*
     *Citiation: Reference:
      *  https://stackoverflow.com/questions/31445156
      *  Under section - "You can invoke camera Activity by adding these lines in your code :"
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFileUri(type));
    }

*/


/*
    public void onClickTakePhoto(View view) {
        Intent takePhotoIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
       File m_file = new File(Environment.getExternalStorageDirectory(), "Pictures");
        m_ouputFileUri = Uri.fromFile(m_file);

        if(takePhotoIntent.resolveActivity(getPackageManager()) !=null){
//            Toast toast = Toast.makeText(this, (CharSequence) m_ouputFileUri, Toast.LENGTH_SHORT);
//            toast.show();
           // startActivityForResult(takePhotoIntent,RETURN_IMAGE);
            startActivity(takePhotoIntent);
        }
    }
    */

    /**
     * Citation: Code Modified from:
     * https://stackoverflow.com/questions/13486814
     * @param view
     */

    public void openEmail(View view) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        Intent openEmailIntent = new Intent(Intent.ACTION_SEND);
        openEmailIntent.setType("text/html");
        openEmailIntent.putExtra(Intent.EXTRA_SUBJECT,
                        m_Name.getText().toString());
        openEmailIntent.putExtra(Intent.EXTRA_TEXT, m_Spinner);
        //openEmailIntent.putExtra(Intent.E)
        openEmailIntent.putExtra(MediaStore.EXTRA_OUTPUT, m_ouputFileUri);
//        openEmailIntent.putExtra(Intent.EXTRA_STREAM, m_ouputFileUri);
      //  openEmailIntent.putExtra(Intent.EXTRA_SUBJECT, (Parcelable) m_Name);
//        openEmailIntent.putExtra("ToValue", m_Name.getText().toString());
//        //startActivity();
//        setResult(RESULT_OK, openEmailIntent);
        //finish();
        startActivity(openEmailIntent.createChooser(openEmailIntent, "Send Email"));

//        sendPrescription = new Intent(this,PrescriptionOrder.class)
//        sendPrescription.putExtra("", edtTextTo.getText().toString());

    }



}
