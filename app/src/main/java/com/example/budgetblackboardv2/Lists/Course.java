package com.example.budgetblackboardv2.Lists;

import android.net.Uri;
import com.example.budgetblackboardv2.Activities.MainActivity;

import java.util.ArrayList;

public class Course {

    private ArrayList<Subject> courseSubjects;

    private String courseName;
    private Uri photo;
    private int image;
    private String description;

    public Course(String courseName,String description,  Uri photo){
        this.courseSubjects = new ArrayList<>();
        this.courseName = courseName;
        this.photo = photo;
        this.description = description;
        MainActivity.courses.add(this);
    }
    public Course(String courseName,String description,  int image){
        this.courseSubjects = new ArrayList<>();
        this.courseName = courseName;
        this.image = image;
        this.description = description;
        MainActivity.courses.add(this);
    }
    public ArrayList<Subject> getCourseSubjects() {
        return courseSubjects;
    }
    public String getCourseName() {
        return courseName;
    }
    public Uri getPhoto() {
        return photo;
    }
    public void addSubject(Subject subject){
        courseSubjects.add(subject);
    }
    public String getDescription() {
        return description;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public int getImage() {
        return image;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
