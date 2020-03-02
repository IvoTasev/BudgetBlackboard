package com.example.budgetblackboardv2.CustomAssets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import android.view.View;

import com.example.budgetblackboardv2.Activities.MainActivity;

public class ProgressBar extends View {

    public ProgressBar(Context context) {
        super(context);
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressBar(Context context,AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();

        Paint green = new Paint();
        green.setColor(Color.GREEN);
        Paint black = new Paint();
        black.setColor(Color.BLACK);


        canvas.drawRect(0, 0, (width/5) * MainActivity.selectedCourse.getCourseSubjects().size(),
                height, black);
        canvas.drawRect(5, 5, (width/5) * MainActivity.selectedCourse.getCourseSubjects().size()-5,
                height-5, green);



//        canvas.drawLine(0f, 0f, width, 0f, black);
//        canvas.drawLine(0f ,0f, 0f, height, black);
//        canvas.drawLine(width, height, height, width, black);


    }
}
