package com.example.budgetblackboardv2.CustomAssets;

import android.content.Context;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.budgetblackboardv2.R;

public class CourseView extends ConstraintLayout {

    private TextView courseName;
    private TextView amountOfSubjectsInCourse;
    private ImageView courseImage;

    public CourseView(Context context) {
        super(context);
        init();
    }

    public CourseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CourseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.course_view, this);
        courseName = findViewById(R.id.courseName);
        amountOfSubjectsInCourse = findViewById(R.id.amountOfSubjectsInCourse);
        courseImage = findViewById(R.id.dialogImage);
    }

    public void addData(String courseName, String amountOfSubjectsInCourse, int courseImage){
        this.courseName.setText(courseName);
        this.amountOfSubjectsInCourse.setText(amountOfSubjectsInCourse + "");
        this.courseImage.setImageResource(courseImage);
    }

    public void addData(String courseName, String amountOfSubjectsInCourse, Uri courseImage) {
        this.courseName.setText(courseName);
        this.amountOfSubjectsInCourse.setText(amountOfSubjectsInCourse + "");
        this.courseImage.setImageURI(courseImage);
    }
}
