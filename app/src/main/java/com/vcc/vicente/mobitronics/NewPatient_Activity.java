package com.vcc.vicente.mobitronics;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class NewPatient_Activity extends AppCompatActivity {

    EditText dob_et;

    public void add_newPatient(MenuItem item) {
        // TODO add patient
        finish();
    }

    public void goto_therapy(MenuItem item) {
        Intent intent = new Intent(this,TherapySettings_Activity.class);
        startActivity(intent);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @SuppressLint("DefaultLocale")
        public void onDateSet(DatePicker view, int year, int month, int day) {
            ((NewPatient_Activity) getActivity()).update_dob_et(year, month, day);
        }
    }

    @SuppressLint("DefaultLocale")
    void update_dob_et(int year, int month, int day) {
        dob_et.setText(String.format("%02d/%02d/%04d",day,month+1,year));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dob_et = findViewById(R.id.dob_et);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.newpatient_menu, menu);
        return true;
    }
}
