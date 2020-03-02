package com.example.budgetblackboardv2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.budgetblackboardv2.Lists.Subject;
import com.example.budgetblackboardv2.Activities.MainActivity;
import com.example.budgetblackboardv2.R;

import java.util.ArrayList;

public class SubjectAdapter extends ArrayAdapter<Subject> {

    Context context;
    ArrayList<Subject> subjects;


    public SubjectAdapter(Context context, ArrayList<Subject> subjects){
        super(context, android.R.layout.simple_list_item_1, subjects);
        this.context = context;
        this.subjects = subjects;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.subject_view, parent, false);
        TextView textView = view.findViewById(R.id.tvSubject);
        textView.setText(MainActivity.selectedCourse.getCourseSubjects().get(position).getSubjectName());
        return view;
    }


}
