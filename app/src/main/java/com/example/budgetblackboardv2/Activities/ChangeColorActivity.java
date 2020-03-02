package com.example.budgetblackboardv2.Activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.budgetblackboardv2.R;

public class ChangeColorActivity extends AppCompatActivity {

    private int red = 0, green = 0, blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_color);

        SeekBar setRed = findViewById(R.id.seekBarRED);
        SeekBar setBlue = findViewById(R.id.seekBarBLUE);
        SeekBar setGreen = findViewById(R.id.seekBarGREEN);
        Button setColor = findViewById(R.id.setColor);
        final View currentColor = findViewById(R.id.currentColor);

        setRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                red = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                currentColor.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });
        setBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                blue = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                currentColor.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });
        setGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                green = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                currentColor.setBackgroundColor(Color.rgb(red, green, blue));
            }
        });

        setColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setStatusBarColor(Color.rgb(red, green, blue));
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.rgb(red, green, blue)));
            }
        });
    }
}
