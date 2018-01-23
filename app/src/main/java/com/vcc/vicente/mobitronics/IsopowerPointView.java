package com.vcc.vicente.mobitronics;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.TextView;

public class IsopowerPointView extends ConstraintLayout {

    private TextView intensityUnit_tv;
    private EditText duration_et;
    private EditText load_et;

    public IsopowerPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.IsopowerPointView, 0, 0);
        String title = "P0";
        try {
            title = a.getString(R.styleable.IsopowerPointView_title);
        } finally {
            a.recycle();
        }

        inflate(context,R.layout.isopower_point_view,this);
        TextView titleTV = findViewById(R.id.title_tv);
        titleTV.setText(title);
        intensityUnit_tv = findViewById(R.id.loadUnit_tv);
        duration_et = findViewById(R.id.time_et);
        load_et = findViewById(R.id.load_et);
    }

    public void setIntensityUnitText(String str) {
        intensityUnit_tv.setText(str);
    }

    public int getDuration() {
        int result;
        try {
            result = Integer.parseInt(duration_et.getText().toString());
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
        }
        return result;
    }

    public int getLoad() {
        int result;
        try {
            result = Integer.parseInt(load_et.getText().toString());
        } catch (Exception e) {
            result = 0;
            e.printStackTrace();
        }
        return result;
    }
}
