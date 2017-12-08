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

public class PrescriptionOrder extends AppCompatActivity
{
    //private String m_To;
    //private String m_Subject;
    //static private final int GET_TEXT_REQUEST_CODE = 1;
    EditText edtText_From;
    //EditText m_Name;
   // EditText m_Message;
    private static final String TAG = "Assign3";
    Uri m_ouputFileUri;
    private static final int REQUEST_SHARE =39714;
    private File m_ImageFile;
    private Uri m_ImageUri;
    String imageFileName = "myImage.jpg";
    String m_SpinnerStr;
    private static String emailTo, emailSubject, emailName, emailMsgPart1, emailMsgPart2, emailRegards;
    Spinner m_SpinnerClick;
    TextView mSpinnerTxtV;
    Spinner spinnerToEmail;
    String spinnerText;
    EditText edtText_Name, edtTxt_OtherInstruc;
    //private static String toText = "To: ", toSubject = "Subject: ", emailAdd, subjectAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prescription);
        edtText_Name = (EditText) findViewById(R.id.edtTextFrom);
       edtTxt_OtherInstruc = (EditText)findViewById(R.id.edTxtDescription);
        //EditText edtTxt_OtherInstruc = (EditText)findViewById(R.id.edTxtDescription);
        //NOT required for retrieving data from input of userName
        //m_Message = (EditText) findViewById(R.id.edTxtDescription);
        //Initialising Strings from resource - To email address
        emailTo = getResources().getString(R.string.ToEmail);
        //Subject
        emailSubject = getResources().getString(R.string.Msg_Subject);
        //Message details
        emailName = getResources().getString(R.string.Msg_Name);
        emailMsgPart1 = getResources().getString(R.string.Msg_Part1);
        emailMsgPart2 = getResources().getString(R.string.Msg_Part2);
        emailRegards = getResources().getString(R.string.Msg_Part3);


       // edtText_From = (EditText) findViewById(R.id.edtTextFrom);
//        spinnerToEmail = (Spinner) findViewById(R.id.spinner);
//        spinnerText = spinnerToEmail.getItemAtPosition().getSelectedItem().toString();

        //Spinner - selection Array Adapater
        m_SpinnerClick = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.collectionTimes,
                android.R.layout.simple_spinner_item);
        m_SpinnerClick.setAdapter(adapter);
        //NOTE - MAY NEED TO PUT THIS OUTSIDE ONCREATE TO RETRIEVE VALUE FOR EMAIL
        m_SpinnerClick.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

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

        // Declare and initialise Message details 'To' and 'Subject', used in last
        // emailAdd = getIntent().getStringExtra("emailTo");
        // subjectAdd = getIntent().getStringExtra("emailSubject");

    }
    /*
    Citation: https://www.youtube.com/watch?v=aApS2W-j8oM (Slidenerd video 104 & 105)
    Spinner Steps:
    - Create the data source,
    define the appearance layoutfile through which the adapter will put data inside the spinner
    define what to do when the user clicks on the spinner using the OnItemSelectedListener
     */



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
     * Citation: Code Modified from:
     * https://stackoverflow.com/questions/13486814
     * @param view
     */

    public String optionalMessage(View view){
       EditText edtTxt_OtherInstruc = (EditText)findViewById(R.id.edTxtDescription);
        if(edtTxt_OtherInstruc.getText().toString() != ""){
        String additionalMessage = "\n\n" + edtTxt_OtherInstruc.getText().toString();
            return additionalMessage;
        }
        return null;
    }

    public void openEmail(View view) {
        //Test Toast to confirm Imagefilepath working
        //Toast.makeText(this, "ImageFilePath is: " + m_ImageFile, Toast.LENGTH_LONG).show();
        //Toast.makeText(this, "Spinner: " + m_SpinnerStr, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "Subject: " + edtText_To.getText().toString(), Toast.LENGTH_SHORT).show();
       // Toast.makeText(this, "Opening Email", Toast.LENGTH_SHORT).show();
        //Intent for requesting to send message
        Intent openEmailIntent = new Intent(Intent.ACTION_SEND);
        openEmailIntent.setType("image/*");
        //Sunject Line from EditText - edtText_Subject
        openEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,emailSubject);
       // openEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,edtText_Name.getText().toString());
        //Email TO: field populated by String Resource
        openEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailTo});

        //openEmailIntent.setType("text/html");
        openEmailIntent.putExtra(Intent.EXTRA_TEXT,
                emailName + " " + edtText_Name.getText().toString() +
                "\n" +
                "\n" + emailMsgPart1 + " " + mSpinnerTxtV.getText().toString() + " " + emailMsgPart2 +
                " " + edtTxt_OtherInstruc.getText().toString() +
                "\n\n" + emailRegards + " " + edtText_Name.getText().toString()
        );
        //Adding for additional instructions. If populated or not
//        if(edtTxt_OtherInstruc.getText().toString() == ""){
//            openEmailIntent.putExtra(Intent.EXTRA_TEXT, "\n");
//        }else {
//            openEmailIntent.putExtra(Intent.EXTRA_TEXT,
//                    "\n" + edtTxt_OtherInstruc.getText().toString());
//        }
        //openEmailIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
        openEmailIntent.setType("message/rfc822");
        //openEmailIntent.putExtra(android.content.Intent.)
        //Type of message set to text/html
       // openEmailIntent.setType("text/html");
        //openEmailIntent.setData(Uri.parse("mailto:"));
        //Enter Persons Name in SubjectLine of email
       // openEmailIntent.putExtra(Intent.EXTRA_SUBJECT, m_Name.getText().toString());
       // openEmailIntent.putExtra(Intent.EXTRA_TEXT, m_SpinnerStr);
        //openEmailIntent.putExtra(Intent.E)
        ///openEmailIntent.putExtra(Intent.EXTRA_TEXT, m_ouputFileUri);
       /// openEmailIntent.putExtra(MediaStore.EXTRA_OUTPUT, m_ouputFileUri);
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




}
