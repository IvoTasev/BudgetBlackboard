package com.example.budgetblackboardv2.Activities;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.budgetblackboardv2.Adapters.CourseAdapter;
import com.example.budgetblackboardv2.Lists.Course;
import com.example.budgetblackboardv2.Lists.Subject;
import com.example.budgetblackboardv2.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Course> courses = new ArrayList<>();
    public static Course selectedCourse;
    public static int coursePosition;
    private static final int CODE_IMAGE = 100;
    Uri imageURI;
    private static boolean appStarted = false;
    protected static CourseAdapter adapter;
    ImageView image;

    CharSequence pleaseFillField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Sanity check needed when screen is rotated (calls onCreate again)
        if (!appStarted) {
            Course physics = new Course("Physics", "e=mc^2", R.drawable.dog_btw);
            physics.addSubject(new Subject("Einstein's most famous quotes"));
            physics.addSubject(new Subject("Kirchhoff laws"));
            Course math = new Course("Math", "", R.drawable.angry_rogier);
            math.addSubject(new Subject("I hate math"));

            Course literature = new Course("Literature", "Et tu, brute?", R.drawable.me_when_lecture_ends);
            literature.addSubject(new Subject("Shake your speak at Shakespeare"));
            appStarted = true;
        }

        final ListView coursesList = findViewById(R.id.courseListView);
        adapter = new CourseAdapter(this, courses);


        coursesList.setAdapter(adapter);

        coursesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCourse = (Course) coursesList.getItemAtPosition(position);
                coursePosition = position;
                startActivity(new Intent(MainActivity.this, CourseActivity.class));

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_course_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addCourse){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
            final View dialogView = getLayoutInflater().inflate(R.layout.add_course_dialog, null);

            pleaseFillField = this.getString(R.string.please_fill_fields);

            final EditText name = dialogView.findViewById(R.id.dialogName);
            final EditText description = dialogView.findViewById(R.id.dialogDescription);
            image = dialogView.findViewById(R.id.dialogImage);
            image.setImageURI(imageURI);

            Button cancel = dialogView.findViewById(R.id.cancel);
            Button addCourse = dialogView.findViewById(R.id.dialogAddCourse);
            Button addPicture = dialogView.findViewById(R.id.dialogAddPicture);

            dialogBuilder.setView(dialogView);
            final AlertDialog dialog = dialogBuilder.create();

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });

            addCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (name.getText().toString().length() <= 0 ) {
                        Toast.makeText(MainActivity.this, pleaseFillField + "", Toast.LENGTH_SHORT).show();
                    }
                    else if (!checkForDuplicates(name.getText().toString())) {
                        Course course = new Course(name.getText().toString(), description.getText().toString(), imageURI);
                        adapter.notifyDataSetChanged();
                        dialog.cancel();
                    } else {
                        Toast.makeText(MainActivity.this, "Course already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            addPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openGallery();
                }
            });
            dialog.show();

        }else if (item.getItemId() == R.id.changeColor) {
            startActivity(new Intent(MainActivity.this, ChangeColorActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, CODE_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK || requestCode == CODE_IMAGE && data != null) {
            imageURI = data.getData();
            image.setImageURI(imageURI);
        }
    }
    public boolean checkForDuplicates(String name){
        for (int i = 0; i < courses.size() ; i++) {
            if (courses.get(i).getCourseName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
