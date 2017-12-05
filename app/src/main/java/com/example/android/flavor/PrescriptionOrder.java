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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.io.File;

import java.util.ArrayList;

import java.util.List;



/**
 * Created by seanh on 30/11/2017.
 * 04/12/2017
 * https://software.intel.com/en-us/blogs/2014/03/25/capturing-handling-displaying-and-sharing-images-in-an-android-app
 */

public class PrescriptionOrder extends AppCompatActivity
{
    EditText m_Name;
    EditText m_Message;
    String TAG = "Assign3";
    Uri m_ouputFileUri;
    private static final int REQUEST_SHARE =39714;
    private File png = null;
    private File m_ImageFile;
    private Uri m_ImageUri;
    String imageFileName = "myImage.jpg";
    String m_ImageFilePath;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescription);
        m_Name = (EditText) findViewById(R.id.edtTextTo);
        m_Message = (EditText) findViewById(R.id.edTxtDescription);

    }

   public void captureImage(View view)
   {
       Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

       m_ImageFile = new File(Environment.getExternalStorageDirectory(),imageFileName);
      m_ImageUri =  Uri.fromFile(m_ImageFile);

       intent.putExtra(MediaStore.EXTRA_OUTPUT, m_ImageUri);
       startActivityForResult(intent, REQUEST_SHARE);//note number of request_share
   }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i(TAG, "Enteres onActivityResult()");
        //Checking request for message:
        if(requestCode == REQUEST_SHARE) {
            if(resultCode == RESULT_OK) {
                Toast.makeText(this, "ImageFilePath is: " + m_ImageFile, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "There was an error saving your accessing your image", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Citation - Code modified from:
     * https://www.mkyong.com/android/android-spinner-drop-down-list-example/
     */
// adds items to spinner dynamically
public void setHoursToSpinner() {
    //Spinner
    Spinner spinner = (Spinner) findViewById(R.id.spinner);
    //Array used to hold Spinner data
    List<String> hour = new ArrayList<String>();
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, hour);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
}

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



    public void openEmail(View view) {
        Intent openEmailIntent = new Intent(Intent.ACTION_SEND);
        openEmailIntent.setType("text/html");
        openEmailIntent.putExtra(Intent.EXTRA_SUBJECT,
                        m_Name.getText().toString());
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
