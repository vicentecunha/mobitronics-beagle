package com.vcc.vicente.mobitronics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TherapySettings_Activity extends AppCompatActivity {

    TextView load_tv;
    IsopowerPointView point1;
    IsopowerPointView point2;
    IsopowerPointView point3;
    IsopowerPointView point4;
    EditText fesTime_et;
    EditText passiveTime_et;
    EditText totalTime_et;
    RadioGroup isopower_rg;
    RadioGroup loadType_rg;

    int fesTime_min = 0;
    int passiveTime_min = 0;
    int totalTime_min = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy_settings);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isopower_rg = findViewById(R.id.isopwer_rg);
        load_tv = findViewById(R.id.load_tv);
        loadType_rg = findViewById(R.id.loadType_rg);
        point1 = findViewById(R.id.point1);
        point2 = findViewById(R.id.point2);
        point3 = findViewById(R.id.point3);
        point4 = findViewById(R.id.point4);

        isopower_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int visibility = View.VISIBLE;
                switch (i) {
                    case R.id.isopowerOn_rb:
                        visibility = View.VISIBLE;
                        break;
                    case R.id.isopowerOff_rb:
                        visibility = View.GONE;
                        break;
                }
                loadType_rg.setVisibility(visibility);
                load_tv.setVisibility(visibility);
                point1.setVisibility(visibility);
                point2.setVisibility(visibility);
                point3.setVisibility(visibility);
                point4.setVisibility(visibility);
            }
        });

        loadType_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String str = "W";
                switch (i) {
                    case R.id.constantLoad_rb:
                        str = "W";
                        break;
                    case R.id.constantTorque_rb:
                        str = "kgfm";
                        break;
                }
                point1.setIntensityUnitText(str);
                point2.setIntensityUnitText(str);
                point3.setIntensityUnitText(str);
                point4.setIntensityUnitText(str);
            }
        });

        fesTime_et = findViewById(R.id.fesTime_et);
        passiveTime_et = findViewById(R.id.passiveTime_et);
        totalTime_et = findViewById(R.id.totalTime_et);

        fesTime_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @SuppressLint("DefaultLocale")
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    fesTime_min = Integer.parseInt(editable.toString());
                } catch (NumberFormatException e) {
                    fesTime_min = 0;
                } finally {
                    totalTime_min = fesTime_min+passiveTime_min;
                    totalTime_et.setText(String.format("%d",totalTime_min));
                }
            }
        });

        passiveTime_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @SuppressLint("DefaultLocale")
            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    passiveTime_min = Integer.parseInt(editable.toString());
                } catch (NumberFormatException e) {
                    passiveTime_min = 0;
                } finally {
                    totalTime_min = fesTime_min+passiveTime_min;
                    totalTime_et.setText(String.format("%d",totalTime_min));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.therapy_menu, menu);
        return true;
    }

    public void goto_channels(MenuItem item) {

        Intent intent = new Intent(this,ChannelSettings_Activity.class);
        intent.putExtra("totalTime_min",totalTime_min);
        int i = isopower_rg.getCheckedRadioButtonId();
        if (i == R.id.isopowerOn_rb) {
            switch (loadType_rg.getCheckedRadioButtonId()) {
                case R.id.constantLoad_rb:
                    intent.putExtra("loadType",LoadType.CONSTANT_LOAD.getValue());
                    break;
                case R.id.constantTorque_rb:
                    intent.putExtra("loadType",LoadType.CONSTANT_TORQUE.getValue());
                    break;
            }
        }
        intent.putExtra("p1_duration",point1.getDuration());
        intent.putExtra("p2_duration",point2.getDuration());
        intent.putExtra("p3_duration",point3.getDuration());
        intent.putExtra("p4_duration",point4.getDuration());
        intent.putExtra("p1_load",point1.getLoad());
        intent.putExtra("p2_load",point2.getLoad());
        intent.putExtra("p3_load",point3.getLoad());
        intent.putExtra("p4_load",point4.getLoad());
        startActivity(intent);
    }
}
