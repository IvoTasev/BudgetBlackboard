package com.example.budgetblackboardv2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.budgetblackboardv2.Lists.Course;
import com.example.budgetblackboardv2.R;
import java.util.ArrayList;

public class CourseAdapter extends ArrayAdapter<Course> {

    Context context;
    ArrayList<Course> courses;


    public CourseAdapter(Context context, ArrayList<Course> courses) {
        super(context, R.layout.course_view, courses);
        this.context = context;
        this.courses = courses;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater rowInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = rowInflater.inflate(R.layout.course_view, parent, false);

        ImageView coursePicture = rowView.findViewById(R.id.dialogImage);

        coursePicture.setImageResource(courses.get(position).getImage());
        if(courses.get(position).getPhoto() != null ) {
            coursePicture.setImageURI(courses.get(position).getPhoto());
        }
        TextView courseName = rowView.findViewById(R.id.courseName);
        courseName.setText(courses.get(position).getCourseName());

        TextView amountOfSubjects = rowView.findViewById(R.id.amountOfSubjectsInCourse);

        CharSequence totalSubjects = context.getString(R.string.total_subjects);
        amountOfSubjects.setText(totalSubjects + ": " + courses.get(position).getCourseSubjects().size());

        return rowView;
    }
}
