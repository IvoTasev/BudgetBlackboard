package com.example.budgetblackboardv2.Activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetblackboardv2.Adapters.SubjectAdapter;
import com.example.budgetblackboardv2.CustomAssets.CourseView;
import com.example.budgetblackboardv2.Lists.Course;
import com.example.budgetblackboardv2.Lists.Subject;
import com.example.budgetblackboardv2.R;

public class CourseActivity extends AppCompatActivity {

    public static Subject selectedSubject;
    public static int subjectPosition;
    protected static SubjectAdapter adapter;
    CourseView cvCurrentCourse;
    CharSequence totalSubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        cvCurrentCourse = findViewById(R.id.cvCurrentCourse);

        Button addSubject = findViewById(R.id.btnAddNewSubject);

        final ListView subjects = findViewById(R.id.lvSubjects);
        TextView description = findViewById(R.id.courseDescription);
        description.setText(MainActivity.selectedCourse.getDescription());

        totalSubjects = this.getString(R.string.total_subjects);
        final CharSequence pleaseFillFields = this.getString(R.string.please_fill_fields);
        final CharSequence courseIsFull = this.getString(R.string.course_is_full);

        adapter = new SubjectAdapter(this, MainActivity.selectedCourse.getCourseSubjects());


        cvCurrentCourse.addData(MainActivity.selectedCourse.getCourseName(), totalSubjects + ": " +
                MainActivity.selectedCourse.getCourseSubjects().size(), MainActivity.selectedCourse.getImage());

        cvCurrentCourse.addData(MainActivity.selectedCourse.getCourseName(), totalSubjects + ": " +
                MainActivity.selectedCourse.getCourseSubjects().size(), MainActivity.selectedCourse.getPhoto());



        subjects.setAdapter(adapter);
        subjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSubject = MainActivity.selectedCourse.getCourseSubjects().get(position);
                subjectPosition = position;
                startActivity(new Intent(CourseActivity.this, SubjectActivity.class));
            }
        });
        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.selectedCourse.getCourseSubjects().size() < 5) {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CourseActivity.this);
                    final View dialogView = getLayoutInflater().inflate(R.layout.add_subject_dialog, null);

                    final EditText name = dialogView.findViewById(R.id.addSubjectName);
                    Button cancel = dialogView.findViewById(R.id.btnAddSubjectCancel);
                    Button addSubject = dialogView.findViewById(R.id.btnAddSubject);

                    dialogBuilder.setView(dialogView);
                    final AlertDialog dialog = dialogBuilder.create();


                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.cancel();
                        }
                    });
                    addSubject.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (name.getText().toString().length() <= 0) {
                                Toast.makeText(CourseActivity.this, pleaseFillFields + "", Toast.LENGTH_SHORT).show();
                            } else if (!checkForDuplicates(name.getText().toString())){
                                MainActivity.selectedCourse.addSubject(new Subject(name.getText().toString()));
                                adapter.notifyDataSetChanged();
                                dialog.cancel();
                            } else {
                                Toast.makeText(CourseActivity.this, "Subject already exists!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog.show();
                } else {
                    Toast.makeText(getBaseContext(), courseIsFull + "", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

//    Updates the compound control
    @Override
    protected void onResume() {
        super.onResume();

        cvCurrentCourse.addData(MainActivity.selectedCourse.getCourseName(), totalSubjects + ": " +
                MainActivity.selectedCourse.getCourseSubjects().size(), MainActivity.selectedCourse.getImage());

        cvCurrentCourse.addData(MainActivity.selectedCourse.getCourseName(), totalSubjects + ": " +
                MainActivity.selectedCourse.getCourseSubjects().size(), MainActivity.selectedCourse.getPhoto());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
                MainActivity.courses.remove(MainActivity.selectedCourse);
                MainActivity.adapter.notifyDataSetChanged();
                finish();
        } else if (item.getItemId() == R.id.edit){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CourseActivity.this);
            final View dialogView = getLayoutInflater().inflate(R.layout.edit_course_dialog, null);

            final EditText name = dialogView.findViewById(R.id.dialogName2);
            final EditText description = dialogView.findViewById(R.id.dialogDescription2);


            Button cancel = dialogView.findViewById(R.id.cancel2);
            Button editCourse = dialogView.findViewById(R.id.dialogAddCourse2);


            name.setText(MainActivity.selectedCourse.getCourseName());
            description.setText(MainActivity.selectedCourse.getDescription());
            final CharSequence pleaseFillFields = this.getString(R.string.please_fill_fields);

            dialogBuilder.setView(dialogView);
            final AlertDialog dialog = dialogBuilder.create();

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
            editCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!name.getText().toString().equals("")) {
                        Course course = MainActivity.courses.get(MainActivity.coursePosition);
                        course.setCourseName(name.getText().toString());
                        course.setDescription(description.getText().toString());
                        MainActivity.courses.set(MainActivity.coursePosition, course);
                        MainActivity.adapter.notifyDataSetChanged();
                        dialog.cancel();
                        finish();
                    } else {
                        Toast.makeText(CourseActivity.this, pleaseFillFields + "", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean checkForDuplicates(String name){
        for (int i = 0; i < MainActivity.selectedCourse.getCourseSubjects().size() ; i++) {
            if (MainActivity.selectedCourse.getCourseSubjects().get(i).getSubjectName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
