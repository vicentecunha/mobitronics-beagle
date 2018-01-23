package com.vcc.vicente.mobitronics;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class TherapyExecution_Activity extends AppCompatActivity {

    int totalTime_min = 0;
    int currentTime_s = 0;
    int speed_rpm = 0;

    TextView timeCounter_tv;
    ImageView play_iv;
    ImageView pause_iv;

    SerialPort ciclo_sp;
    InputStream ciclo_is;
    OutputStream ciclo_os;
    byte[] ciclo_buf = new byte[16];
    int ciclo_bufIndex = 0;

    SerialPort stim_sp;
    InputStream stim_is;
    OutputStream stim_os;

    Channel[] channels = new Channel[8];
    IsopowerPoint[] isopowerPoints = new IsopowerPoint[4];
    int loadType;

    int loadFromMin(int min) {
        int totalTime_min = 0;
        for (IsopowerPoint isopowerPoint : isopowerPoints) {
            totalTime_min += isopowerPoint.getDuration();
            if (min < totalTime_min) return isopowerPoint.getLoad();
        }
        return isopowerPoints[3].getLoad();
    }

    @SuppressLint("DefaultLocale")
    void sendLoad(int load_W) {
        try {
            ciclo_os.write((byte) 0xA0);
            if (speed_rpm >= 15) {
                ciclo_os.write(String.format("%04d", load_W).getBytes());
            } else {
                ciclo_os.write("0000".getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    CountDownTimer countDownTimer = new CountDownTimer(1000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {}
        @SuppressLint("DefaultLocale")
        @Override
        public void onFinish() {
            currentTime_s += 1;
            int min_arg = currentTime_s/60;
            int s_arg = currentTime_s%60;
            timeCounter_tv.setText(String.format("%02d:%02d",min_arg,s_arg));

            try {
                int available = ciclo_is.available();
                if (available > 0) {
                   int r = ciclo_is.read(ciclo_buf, ciclo_bufIndex, available);
                   ciclo_bufIndex += r;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String string = new String(ciclo_buf);
            int i = string.indexOf('B');
            if (i >= 0 && ciclo_bufIndex-i > 3) {
                try {
                    speed_rpm = Integer.parseInt(new String(Arrays.copyOfRange(ciclo_buf, i + 1, 3)));
                } catch (Exception e) {
                    speed_rpm = 0;
                    e.printStackTrace();
                } finally {
                    ciclo_buf = new byte[16];
                    ciclo_bufIndex = 0;
                }
            }

            int load = loadFromMin(min_arg);
            if (loadType == LoadType.CONSTANT_LOAD.getValue()) {
                sendLoad(load);
            } else if (loadType == LoadType.CONSTANT_TORQUE.getValue()) {
                sendLoad(load*speed_rpm);
            }

            this.start();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy_execution);

        totalTime_min = getIntent().getIntExtra("totalTime_min",totalTime_min);
        loadType = getIntent().getIntExtra("loadType",0);
        int intensity_1a = getIntent().getIntExtra("intensity_1a",0);
        int frequency_1a = getIntent().getIntExtra("frequency_1a",0);
        int width_1a = getIntent().getIntExtra("width_1a",0);
        int intensity_1b = getIntent().getIntExtra("intensity_1b",0);
        int frequency_1b = getIntent().getIntExtra("frequency_1b",0);
        int width_1b = getIntent().getIntExtra("width_1b",0);
        int intensity_2a = getIntent().getIntExtra("intensity_2a",0);
        int frequency_2a = getIntent().getIntExtra("frequency_2a",0);
        int width_2a = getIntent().getIntExtra("width_2a",0);
        int intensity_2b = getIntent().getIntExtra("intensity_2b",0);
        int frequency_2b = getIntent().getIntExtra("frequency_2b",0);
        int width_2b = getIntent().getIntExtra("width_2b",0);
        int intensity_3a = getIntent().getIntExtra("intensity_3a",0);
        int frequency_3a = getIntent().getIntExtra("frequency_3a",0);
        int width_3a = getIntent().getIntExtra("width_3a",0);
        int intensity_3b = getIntent().getIntExtra("intensity_3b",0);
        int frequency_3b = getIntent().getIntExtra("frequency_3b",0);
        int width_3b = getIntent().getIntExtra("width_3b",0);
        int intensity_4a = getIntent().getIntExtra("intensity_4a",0);
        int frequency_4a = getIntent().getIntExtra("frequency_4a",0);
        int width_4a = getIntent().getIntExtra("width_4a",0);
        int intensity_4b = getIntent().getIntExtra("intensity_4b",0);
        int frequency_4b = getIntent().getIntExtra("frequency_4b",0);
        int width_4b = getIntent().getIntExtra("width_4b",0);
        int p1_duration = getIntent().getIntExtra("p1_duration",0);
        int p1_load = getIntent().getIntExtra("p1_load",0);
        int p2_duration = getIntent().getIntExtra("p2_duration",0);
        int p2_load = getIntent().getIntExtra("p2_load",0);
        int p3_duration = getIntent().getIntExtra("p3_duration",0);
        int p3_load = getIntent().getIntExtra("p3_load",0);
        int p4_duration = getIntent().getIntExtra("p4_duration",0);
        int p4_load = getIntent().getIntExtra("p4_load",0);

        channels[0] = new Channel(intensity_1a,frequency_1a,width_1a);
        channels[1] = new Channel(intensity_1b,frequency_1b,width_1b);
        channels[2] = new Channel(intensity_2a,frequency_2a,width_2a);
        channels[3] = new Channel(intensity_2b,frequency_2b,width_2b);
        channels[4] = new Channel(intensity_3a,frequency_3a,width_3a);
        channels[5] = new Channel(intensity_3b,frequency_3b,width_3b);
        channels[6] = new Channel(intensity_4a,frequency_4a,width_4a);
        channels[7] = new Channel(intensity_4b,frequency_4b,width_4b);

        isopowerPoints[0] = new IsopowerPoint(p1_duration,p1_load);
        isopowerPoints[1] = new IsopowerPoint(p2_duration,p2_load);
        isopowerPoints[2] = new IsopowerPoint(p3_duration,p3_load);
        isopowerPoints[3] = new IsopowerPoint(p4_duration,p4_load);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        timeCounter_tv = findViewById(R.id.timeCounter_tv);
        play_iv = findViewById(R.id.play_iv);
        pause_iv = findViewById(R.id.pause_iv);

        ChannelView channelView_1a = findViewById(R.id.channel_1a);
        ChannelView channelView_2a = findViewById(R.id.channel_2a);
        ChannelView channelView_3a = findViewById(R.id.channel_3a);
        ChannelView channelView_4a = findViewById(R.id.channel_4a);
        ChannelView channelView_1b = findViewById(R.id.channel_1b);
        ChannelView channelView_2b = findViewById(R.id.channel_2b);
        ChannelView channelView_3b = findViewById(R.id.channel_3b);
        ChannelView channelView_4b = findViewById(R.id.channel_4b);
        channelView_1a.setChannelInfo(channels[0]);
        channelView_1b.setChannelInfo(channels[1]);
        channelView_2a.setChannelInfo(channels[2]);
        channelView_2b.setChannelInfo(channels[3]);
        channelView_3a.setChannelInfo(channels[4]);
        channelView_3b.setChannelInfo(channels[5]);
        channelView_4a.setChannelInfo(channels[6]);
        channelView_4b.setChannelInfo(channels[7]);

        try {
            ciclo_sp = new SerialPort(new File("/dev/ttyO1"),9600,0);
            stim_sp = new SerialPort(new File("/dev/ttyO2"),9600,0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ciclo_is = ciclo_sp.getInputStream();
            ciclo_os = ciclo_sp.getOutputStream();
            stim_is = stim_sp.getInputStream();
            stim_os = stim_sp.getOutputStream();
        }

        SempraFrame sempraFrame = new SempraFrame(SempraMode.AUTOMATICO,channels);
        sempraFrame.send(stim_os);
    }

    public void playHandler(View view) {

        countDownTimer.start();
        view.setVisibility(View.INVISIBLE);
        pause_iv.setVisibility(View.VISIBLE);
    }

    public void pauseHandler(View view) {
        countDownTimer.cancel();
        view.setVisibility(View.INVISIBLE);
        play_iv.setVisibility(View.VISIBLE);
    }
}
