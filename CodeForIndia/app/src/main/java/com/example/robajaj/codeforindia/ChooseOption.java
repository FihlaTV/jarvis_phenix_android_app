package com.example.robajaj.codeforindia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseOption extends Activity {

    String teacherId;
    String examinationText;
    String password;
    RadioGroup courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_option);


        Intent intent = getIntent();
        teacherId = intent.getStringExtra("TEACHER_ID");
        password = intent.getStringExtra("PASSWORD");
        examinationText = intent.getStringExtra("EXAMINATION_TEXT");

        courses = (RadioGroup) findViewById(R.id.courses);
        //call the server on basis of teacher id and add them in RadioGroup

        String[] coursesArray = {"Hindi - Class 5","Hindi - Class 6" , "Hindi - Class 7", "Sanskrit - Class 1" , "Sanskrit - Class 2", "Sanskrit - Class 3"};

        for (int i = 0; i < 6; i++) {
            RadioButton r = new RadioButton(this);
            r.setId(i);
            r.setText(coursesArray[i]); // change it to the value received from server
            courses.addView(r);
        }

    }
    public void takeAttendance(View view){

        if(courses.getCheckedRadioButtonId() == -1) {
         //do nothing
        }
        else
        {
            int selectedId = courses.getCheckedRadioButtonId();

            // find the radiobutton by returned id
            RadioButton r = (RadioButton) findViewById(selectedId);

            Toast.makeText(this,
                    r.getText(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, TakeAttendance.class);
            intent.putExtra("COURSE_SELECTED_ID",r.getId() + 1);
            intent.putExtra("COURSE_SELECTED",r.getText());
            this.startActivity(intent);
        }
    }

    public void viewPreviousAttendance(View view){

        int selectedId = courses.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        RadioButton r = (RadioButton) findViewById(selectedId);

        Toast.makeText(this,
                r.getText(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ViewPreviousAttendance.class);
        intent.putExtra("COURSE_SELECTED",r.getText());
        this.startActivity(intent);

    }
}