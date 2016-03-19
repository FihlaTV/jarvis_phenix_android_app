package com.example.robajaj.codeforindia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class StartupActivity extends AppCompatActivity {

    CheckBox mShowPassword;
    CheckBox mShowExamination;
    EditText mPasswordText;
    EditText mExaminationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        mShowPassword = (CheckBox) findViewById(R.id.show_login_password);
        mPasswordText = (EditText) findViewById(R.id.login_password);
        mShowExamination = (CheckBox) findViewById(R.id.examinationCheckbox);
        mExaminationText = (EditText) findViewById(R.id.examinationText);




        mShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    mPasswordText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mPasswordText.setSelection(mPasswordText.getText().length());
                } else {
                    // hide password
                    mPasswordText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mPasswordText.setSelection(mPasswordText.getText().length());
                }
            }
        });

        mShowExamination.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    mExaminationText.setVisibility(View.INVISIBLE);
                } else {
                    mExaminationText.setVisibility(View.VISIBLE);
                }
            }
        });

   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_startup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void loginUser(View view) {
        String teacherID = ((EditText) findViewById(R.id.login_email_address)).getText().toString();
        String password = ((EditText) findViewById(R.id.login_password)).getText().toString();
        Boolean examination = ((CheckBox) findViewById(R.id.examinationCheckbox)).isChecked();
        String examinationText;
        if (examination)
            examinationText = ((EditText) findViewById(R.id.examinationText)).getText().toString();
        else
            examinationText = "Nothing Specified";

        if (examination) {
            Intent intent = new Intent(this, TakeAttendance.class);
            intent.putExtra("EXAMINATION_TEXT", examinationText);
            intent.putExtra("TEACHER_ID", teacherID);
            intent.putExtra("PASSWORD", password);
            this.startActivity(intent);

        } else {
            Intent intent = new Intent(this, ChooseOption.class);
            intent.putExtra("EXAMINATION_TEXT", examinationText);
            intent.putExtra("TEACHER_ID", teacherID);
            intent.putExtra("PASSWORD", password);
            this.startActivity(intent);
        }
    }
}
