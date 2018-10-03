package com.example.user.bluetooth_swipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import static com.example.user.bluetooth_swipe.bluetooth.mSocket;
import static java.lang.String.valueOf;


public class clock extends Fragment {
    TextView textView;
    TimePicker timePicker;
    int h,m;
    ConnectedThread connectedThread;
    public clock() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*bluetooth a = new bluetooth();
        a.connectedThread.write("0");*/
        return inflater.inflate(R.layout.fragment_clock, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView = (TextView)getView().findViewById(R.id.clocktext);
        timePicker =(TimePicker)getView().findViewById(R.id.time);
        timePicker.setIs24HourView(true);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                connectedThread = new ConnectedThread(mSocket);
                connectedThread.start();
                h=hourOfDay;
                m=minute;
                if(h<10 && m>10) {
                    textView.setText("0"+ h + ":" + m);
                    connectedThread.write("0");
                    connectedThread.write(valueOf(h));
                    connectedThread.write(valueOf(m));
                }
                else if (m<10 && h>10)
                {
                    textView.setText(h + ":" +"0"+ m);
                    connectedThread.write(valueOf(h));
                    connectedThread.write("0");
                    connectedThread.write(valueOf(m));
                }
                else if (h<10 && m<10)
                {
                    textView.setText("0"+ h + ":" +"0"+ m);
                    connectedThread.write("0");
                    connectedThread.write(valueOf(h));
                    connectedThread.write("0");
                    connectedThread.write(valueOf(m));
                }
                else
                {
                    textView.setText(h + ":" + m);
                    connectedThread.write(valueOf(h));
                    connectedThread.write(valueOf(m));
                }

            }

        });
    }

}

