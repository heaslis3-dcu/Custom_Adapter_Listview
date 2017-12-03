package com.example.android.flavor;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

/**
 * Created by seanh on 30/11/2017.
 */

public class PrescriptionOrder extends AppCompatActivity
{
    EditText m_Name;
    EditText m_Message;
    Spinner m_Time;
    Image m_Photo;
    File m_file;
    Uri m_ouputFileUri;
//    String m_Subject = getString(R.string.Email_Subject);
//    String m_ToEmail [] = {getString(R.string.ToEmail)};
//    String m_FromEmail [] = {getString(R.string.FromEmail)};

    /*
    private static Intent sendPrescription;
    private static final int RETURN_IMAGE = 2;
    String TAG = "Assign3";

    EditText edTxtTo = (EditText) findViewById(R.id.edtTextTo);
    EditText edTxtDesc = (EditText) findViewById(R.id.edTxtDescription);

    */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescription);
        m_Name = (EditText) findViewById(R.id.edtTextTo);
        m_Message = (EditText) findViewById(R.id.edTxtDescription);
       //m_Photo =
    }

    public void onClickTakePhoto(View view) {
        Intent takePhotoIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        m_file = new File(Environment.getExternalStorageDirectory(), "myImage.jpg");
        m_ouputFileUri = Uri.fromFile(m_file);

        if(takePhotoIntent.resolveActivity(getPackageManager()) !=null){
//            Toast toast = Toast.makeText(this, (CharSequence) m_ouputFileUri, Toast.LENGTH_SHORT);
//            toast.show();
           // startActivityForResult(takePhotoIntent,RETURN_IMAGE);
            startActivity(takePhotoIntent);
        }
    }

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

/*
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            Log.i(TAG, "Enteres onActivityResult()");
        //Checking request:
        if(requestCode == RETURN_IMAGE)
            if(requestCode == RESULT_OK) {


        }
    }
*/
}
