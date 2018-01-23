package com.vcc.vicente.mobitronics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

public class ChannelView extends ConstraintLayout {

    private TextView intensity_tv;
    private TextView frequency_tv;
    private TextView width_tv;

    public ChannelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ChannelView, 0, 0);
        String title = "Canal 1A";
        try {
            title = a.getString(R.styleable.ChannelView_channelName);
        } finally {
            a.recycle();
        }

        inflate(context,R.layout.channel_view,this);
        TextView titleTV = findViewById(R.id.channelName_tv);
        titleTV.setText(title);
        intensity_tv = findViewById(R.id.intensity_tv);
        frequency_tv = findViewById(R.id.frequency_tv);
        width_tv = findViewById(R.id.width_tv);
    }

    @SuppressLint("DefaultLocale")
    public void setChannelInfo(Channel channel) {
        intensity_tv.setText(String.format("Intensidade: %d",channel.getIntensity()));
        frequency_tv.setText(String.format("Frequência: %d Hz",channel.getFrequency()));
        width_tv.setText(String.format("Largura de Pulso: %d",channel.getWidth()));
    }
}
