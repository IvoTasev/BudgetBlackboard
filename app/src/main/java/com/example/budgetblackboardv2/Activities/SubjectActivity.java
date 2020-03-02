package com.example.budgetblackboardv2.Activities;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetblackboardv2.Lists.Subject;
import com.example.budgetblackboardv2.R;

public class SubjectActivity extends AppCompatActivity {

    private int currentCourseSubjects = MainActivity.selectedCourse.getCourseSubjects().size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        CharSequence theCourseCurrentlyHas = this.getString(R.string.course_currently_has);
        CharSequence outOf5Subjects = this.getString(R.string.out_of_5_subjects);

        TextView selectedSubject = findViewById(R.id.selectedSubject);
        selectedSubject.setText(CourseActivity.selectedSubject.getSubjectName());

        TextView tvAmountOfSubjectsBar = findViewById(R.id.tvAmountOfSubjectsBar);
        tvAmountOfSubjectsBar.setText(theCourseCurrentlyHas + "" + currentCourseSubjects
                + outOf5Subjects + "");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
                MainActivity.selectedCourse.getCourseSubjects().remove(CourseActivity.selectedSubject);
                currentCourseSubjects--;
                MainActivity.adapter.notifyDataSetChanged();
                CourseActivity.adapter.notifyDataSetChanged();
                finish();
        } else if (item.getItemId() == R.id.edit){
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(SubjectActivity.this);
                final View dialogView = getLayoutInflater().inflate(R.layout.edit_subject_dialog, null);

                final EditText name = dialogView.findViewById(R.id.subjectName);

                Button editSubject = dialogView.findViewById(R.id.editSubject);
                Button cancel = dialogView.findViewById(R.id.editSubjectCancel);

                name.setText(CourseActivity.selectedSubject.getSubjectName());

                final CharSequence pleaseFillFields = this.getString(R.string.please_fill_fields);


                dialogBuilder.setView(dialogView);
                final AlertDialog dialog = dialogBuilder.create();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                editSubject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!name.getText().toString().equals("")) {
                            Subject subject = CourseActivity.selectedSubject;
                            subject.setSubjectName(name.getText().toString());
                            MainActivity.selectedCourse.getCourseSubjects().set(CourseActivity.subjectPosition, subject);
                            MainActivity.adapter.notifyDataSetChanged();
                            CourseActivity.adapter.notifyDataSetChanged();
                            dialog.cancel();
                            finish();
                        } else {
                            Toast.makeText(SubjectActivity.this, pleaseFillFields + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

}
