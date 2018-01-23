package com.vcc.vicente.mobitronics;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ChannelSettings_Activity extends AppCompatActivity {

    int totalTime_min = 0;
    int loadType = 0;
    static Channel[] channels = new Channel[8];
    IsopowerPoint isopowerPoints[] = new IsopowerPoint[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_settings);

        totalTime_min = getIntent().getIntExtra("totalTime_min",totalTime_min);
        loadType = getIntent().getIntExtra("loadType",0);
        int p1_duration = getIntent().getIntExtra("p1_duration",0);
        int p1_load = getIntent().getIntExtra("p1_load",0);
        int p2_duration = getIntent().getIntExtra("p2_duration",0);
        int p2_load = getIntent().getIntExtra("p2_load",0);
        int p3_duration = getIntent().getIntExtra("p3_duration",0);
        int p3_load = getIntent().getIntExtra("p3_load",0);
        int p4_duration = getIntent().getIntExtra("p4_duration",0);
        int p4_load = getIntent().getIntExtra("p4_load",0);

        isopowerPoints[0] = new IsopowerPoint(p1_duration,p1_load);
        isopowerPoints[1] = new IsopowerPoint(p2_duration,p2_load);
        isopowerPoints[2] = new IsopowerPoint(p3_duration,p3_load);
        isopowerPoints[3] = new IsopowerPoint(p4_duration,p4_load);

        for (int i = 0; i < channels.length; i++) {
            channels[i] = new Channel();
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.channel_menu, menu);
        return true;
    }

    public void goto_exam(MenuItem item) {
        Intent intent = new Intent(this,TherapyExecution_Activity.class);
        intent.putExtra("totalTime_min",totalTime_min);
        intent.putExtra("loadType",loadType);
        intent.putExtra("intensity_1a",channels[0].getIntensity());
        intent.putExtra("frequency_1a",channels[0].getFrequency());
        intent.putExtra("width_1a",channels[0].getWidth());
        intent.putExtra("intensity_1b",channels[1].getIntensity());
        intent.putExtra("frequency_1b",channels[1].getFrequency());
        intent.putExtra("width_1b",channels[1].getWidth());
        intent.putExtra("intensity_2a",channels[2].getIntensity());
        intent.putExtra("frequency_2a",channels[2].getFrequency());
        intent.putExtra("width_2a",channels[2].getWidth());
        intent.putExtra("intensity_2b",channels[3].getIntensity());
        intent.putExtra("frequency_2b",channels[3].getFrequency());
        intent.putExtra("width_2b",channels[3].getWidth());
        intent.putExtra("intensity_3a",channels[4].getIntensity());
        intent.putExtra("frequency_3a",channels[4].getFrequency());
        intent.putExtra("width_3a",channels[4].getWidth());
        intent.putExtra("intensity_3b",channels[5].getIntensity());
        intent.putExtra("frequency_3b",channels[5].getFrequency());
        intent.putExtra("width_3b",channels[5].getWidth());
        intent.putExtra("intensity_4a",channels[6].getIntensity());
        intent.putExtra("frequency_4a",channels[6].getFrequency());
        intent.putExtra("width_4a",channels[6].getWidth());
        intent.putExtra("intensity_4b",channels[7].getIntensity());
        intent.putExtra("frequency_4b",channels[7].getFrequency());
        intent.putExtra("width_4b",channels[7].getWidth());
        intent.putExtra("p1_duration",isopowerPoints[0].getDuration());
        intent.putExtra("p1_load",isopowerPoints[0].getLoad());
        intent.putExtra("p2_duration",isopowerPoints[1].getDuration());
        intent.putExtra("p2_load",isopowerPoints[1].getLoad());
        intent.putExtra("p3_duration",isopowerPoints[2].getDuration());
        intent.putExtra("p3_load",isopowerPoints[2].getLoad());
        intent.putExtra("p4_duration",isopowerPoints[3].getDuration());
        intent.putExtra("p4_load",isopowerPoints[3].getLoad());
        startActivity(intent);
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {}

        public static PlaceholderFragment newInstance(int position) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position",position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_channel, container, false);
            final int position = getArguments().getInt("position");

            EditText intensity_et = rootView.findViewById(R.id.load_et);
            intensity_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void afterTextChanged(Editable editable) {
                    try {
                        channels[position].setIntensity(Integer.parseInt(editable.toString()));
                    } catch (Exception e) {
                        channels[position].setIntensity(0);
                        e.printStackTrace();
                    }
                }
            });

            EditText frequency_et = rootView.findViewById(R.id.frequency_et);
            frequency_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void afterTextChanged(Editable editable) {
                    try {
                        channels[position].setFrequency(Integer.parseInt(editable.toString()));
                    } catch (Exception e) {
                        channels[position].setFrequency(0);
                        e.printStackTrace();
                    }
                }
            });

            EditText pulse_et = rootView.findViewById(R.id.pulse_et);
            pulse_et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
                @Override
                public void afterTextChanged(Editable editable) {
                    try {
                        channels[position].setWidth(Integer.parseInt(editable.toString()));
                    } catch (Exception e) {
                        channels[position].setWidth(0);
                        e.printStackTrace();
                    }
                }
            });
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 8;
        }
    }
}
