package com.vcc.vicente.mobitronics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PatientAdapter extends BaseAdapter {

    private Context context;

    // TODO example patient, remove it for final version
    final Patient John_Doe = new Patient(1,"John Doe",
            new GregorianCalendar(1980,Calendar.DECEMBER,25), Patient.Gender.Masculino);

    PatientAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO retornar número de pacientes cadastrados
        return 16;
    }

    @Override
    public Object getItem(int i) {
        // TODO retornar paciente na posição 'i'
        return John_Doe;
    }

    @Override
    public long getItemId(int i) {
        // TODO retornar id do paciente na posição 'i'
        return John_Doe.getId();
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.patient_view,viewGroup,false);
        }

        final TextView id_tv = v.findViewById(R.id.id_tv);
        TextView name_tv = v.findViewById(R.id.name_tv);
        TextView dob_tv = v.findViewById(R.id.dob_tv);
        TextView dolt_tv = v.findViewById(R.id.dolt_tv);
        TextView gender_tv = v.findViewById(R.id.gender_tv);
        TextView therapyCount_tv = v.findViewById(R.id.therapyCount_tv);

        // TODO atualizar corretamente as visualizações, de acordo com posição 'i'
        id_tv.setText(String.format("%04d",i));
        name_tv.setText(John_Doe.getName());
        GregorianCalendar dob = John_Doe.getDateOfBirth();
        dob_tv.setText(String.format("Data de Nascimento: %d/%d/%d",dob.get(Calendar.DAY_OF_MONTH),dob.get(Calendar.MONTH)+1,dob.get(Calendar.YEAR)));
        GregorianCalendar dolt = John_Doe.getDateOfLastTherapy();
        dolt_tv.setText(String.format("Data do Último Teste: %d/%d/%d",dolt.get(Calendar.DAY_OF_MONTH),dolt.get(Calendar.MONTH)+1,dolt.get(Calendar.YEAR)));
        gender_tv.setText("Gênero: "+ John_Doe.getGender().toString());
        therapyCount_tv.setText(String.format("Número de Terapias: %d",John_Doe.getTherapyCount()));

        ImageView history_iv = v.findViewById(R.id.history_iv);
        history_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO abrir histórico
            }
        });

        ImageView therapy_iv = v.findViewById(R.id.therapy_iv);
        therapy_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,TherapySettings_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return v;
    }
}
