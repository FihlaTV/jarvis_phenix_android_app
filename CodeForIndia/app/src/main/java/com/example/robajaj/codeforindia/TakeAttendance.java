package com.example.robajaj.codeforindia;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.robajaj.codeforindia.request.RequestExecCallback;
import com.example.robajaj.codeforindia.request.RequestExecTask;
import com.example.robajaj.codeforindia.students.GetStudentCountRequest;
import com.example.robajaj.codeforindia.students.GetStudentCountResponse;

public class TakeAttendance extends Activity {

    String mCourseSelected;
    int mCourseSelectedId = 1;
    TextView mCourseName;
    int countPresent = 0;
    TextView mPresentText;
    boolean mErrorDisplayed = false;

    TextView mErrorTextView;
    View mErrorLayoutView;
    Button mGiveAttendanceButton;
    EditText mVerifyCode;

    private static final String TAG = "TakeAttendance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);


        Intent intent = getIntent();
        mCourseSelected = intent.getStringExtra("COURSE_SELECTED");
        mCourseSelectedId = intent.getIntExtra("COURSE_SELECTED_ID", 1);
        if(mCourseSelected == null){
            mCourseSelected = intent.getStringExtra("EXAMINATION_TEXT");
        }
        mCourseName = (TextView) findViewById(R.id.courseName);
        mCourseName.setText(mCourseSelected);

        mPresentText = (TextView) findViewById(R.id.presentCountText);

        updateTotalStudentCount();

        mGiveAttendanceButton = (Button) findViewById(R.id.give_attendance);
        mErrorLayoutView =  findViewById(R.id.error_verification_code);
        mErrorTextView = (TextView) findViewById(R.id.error_text);
        mVerifyCode = (EditText) findViewById(R.id.verification_code);
    }

    public void authenticateUser(View view){

        Toast.makeText(this,
                "Place your finger on scanner", Toast.LENGTH_SHORT).show();


        showDialogForFingerprint(this);
       /* final ProgressDialog dialog = ProgressDialog.show(this, "", "Verifying...",
                true);
        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, 3000);  // 3000 milliseconds


        countPresent++;


        mPresentText.setText("Present : " + countPresent );*/
    }

    public void updateTotalStudentCount() {
        final TextView countView = (TextView) TakeAttendance.this.findViewById(R.id.student_count);

        GetStudentCountRequest request = new GetStudentCountRequest(this, mCourseSelectedId);
        RequestExecTask<GetStudentCountRequest, GetStudentCountResponse> execTask = new RequestExecTask<>(
                new RequestExecCallback<GetStudentCountResponse>() {
                    @Override
                    public void onPostRequestExec(GetStudentCountResponse result) {
                        if (result != null && result.isSuccess()) {
                            countView.setText("Total Count : " + result.getCount());
                            Log.e(TAG, result.getHttpStatusCode() + ": " + result.getHttpStatusText());
                        } else {
                            Log.e(TAG, "Result was null");
                        }

                    }
                }
        );
        execTask.execute(request);
    }

    AlertDialog mScannerDialog = null;

    public void showDialogForFingerprint(final Context context)

    {

        mScannerDialog = new AlertDialog.Builder(this)

                .setTitle("FingerPrint scanner")

                .setOnCancelListener(new DialogInterface.OnCancelListener() {

                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                })

                .setView(LayoutInflater.from(this).inflate(R.layout.fingerprint_scanner,null))

                .setPositiveButton("OK", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which)

                    {

                        dialog.dismiss();


                    }})

                .setCancelable(true)

                .show();



    }

    public void onScannerClick(View v)

    {

        new Thread(new Runnable()

        {
            public void run()
            {
                // Toast message don't work here
                try
                {
                    Thread.sleep(3000);
                }

                catch(Exception e)

                {



                }

                TakeAttendance.this.runOnUiThread(new Runnable() {



                    @Override

                    public void run() {

                        if( mScannerDialog != null && mScannerDialog.isShowing())

                        {

                            mScannerDialog.dismiss();

                            if( countPresent%3 == 0 && mErrorDisplayed == false && countPresent != 0)
                            {

                                mErrorDisplayed = true;
                                mGiveAttendanceButton.setEnabled(false);
                                mErrorLayoutView.setVisibility(View.VISIBLE);
                                mErrorTextView.setVisibility(View.VISIBLE);
                            }
                            else {
                                countPresent++;
                                mPresentText.setText("Present : " + countPresent);
                                mErrorDisplayed = false;
                            }

                        }

                    }

                });

            }

        }).start();

    }

    public void verifyCode(View v)
    {
        String code = mVerifyCode.getText().toString();

        if( code.equals("1234") ) {
            mGiveAttendanceButton.setEnabled(true);
            mErrorLayoutView.setVisibility(View.GONE);
            mErrorTextView.setVisibility(View.GONE);
            mVerifyCode.setText("0");
        }
        else
        {
            Toast.makeText(TakeAttendance.this, "Incorrect code. Please try again", Toast.LENGTH_LONG).show();
        }
    }

}
