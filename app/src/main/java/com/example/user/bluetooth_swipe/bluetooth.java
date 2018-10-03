package com.example.user.bluetooth_swipe;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;


public class bluetooth extends Fragment {
    Button btn_connect,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_clr;
    Button btn_up,btn_down,btn_right,btn_left;
    TextView textView;
    RadioGroup  radioGroup;
    RadioButton rbt_1,rbt_2,rbt_3,rbt_4,rbt_5,rbt_6;
    private  static final int REQUEST_ENABLE_BT=1;
    private  static final int REQUEST_ENABLE_BT_connect=2;
    private  static final int MESSAGE_READ=3;

    ConnectedThread connectedThread;

    Handler mHandler;
    StringBuilder builder = new StringBuilder();
    BluetoothAdapter BluetoothAdaper = null;
    BluetoothDevice mDrvice = null;
    public static BluetoothSocket mSocket = null;

    boolean mconnect = false;

    private static String MAC=null;

    UUID uuid=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bluetooth, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        btn_connect=(Button)getView().findViewById(R.id.connect);
        btn_1=(Button)getView().findViewById(R.id.btn1);
        btn_2=(Button)getView().findViewById(R.id.btn2);
        btn_3=(Button)getView().findViewById(R.id.btn3);
        btn_4=(Button)getView().findViewById(R.id.btn4);
        btn_5=(Button)getView().findViewById(R.id.btn5);
        btn_6=(Button)getView().findViewById(R.id.btn6);
        btn_7=(Button)getView().findViewById(R.id.btn7);
        btn_8=(Button)getView().findViewById(R.id.btn8);
        btn_clr=(Button)getView().findViewById(R.id.btnclr);
        btn_up=(Button)getView().findViewById(R.id.btn_up);
        btn_down=(Button)getView().findViewById(R.id.btn_down);
        btn_right=(Button)getView().findViewById(R.id.btn_right);
        btn_left=(Button)getView().findViewById(R.id.btn_left);
        textView=(TextView)getView().findViewById(R.id.textview);
        radioGroup=(RadioGroup) getView().findViewById(R.id.radiogroup);
        rbt_1=(RadioButton)getView().findViewById(R.id.rbt1);
        rbt_2=(RadioButton)getView().findViewById(R.id.rbt2);
        rbt_3=(RadioButton)getView().findViewById(R.id.rbt3);
        rbt_4=(RadioButton)getView().findViewById(R.id.rbt4);
        rbt_5=(RadioButton)getView().findViewById(R.id.rbt5);
        rbt_6=(RadioButton)getView().findViewById(R.id.rbt6);

        BluetoothAdaper = BluetoothAdapter.getDefaultAdapter();

        if(BluetoothAdaper==null)
        {
            Toast.makeText(getContext(),"null",Toast.LENGTH_LONG).show();
        }
        else if(!BluetoothAdaper.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent,REQUEST_ENABLE_BT);
        }

        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mconnect)
                {
                    try {
                        mSocket.close();
                        mconnect=false;
                        btn_connect.setText("connect");

                        Toast.makeText(getContext()," bluetooth is already connect" +  MAC,Toast.LENGTH_LONG).show();

                    }catch (IOException erro){
                        Toast.makeText(getContext()," connect exceptoin" +  erro,Toast.LENGTH_LONG).show();

                    }
                }
                else
                {
                    Intent viewlist= new Intent(getActivity(),ListStatus.class);
                    startActivityForResult(viewlist,REQUEST_ENABLE_BT_connect);
                }
            }
        });
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("1");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("2");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("3");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("4");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("5");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("6");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("7");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("8");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("r");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("u");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("n");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("t");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mconnect)
                {
                    connectedThread.write("l");
                }
                else {
                    Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
                }
            }
        });



        mHandler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == MESSAGE_READ)
                {
                    String receive =(String) msg.obj;
                    builder.append(receive);
                    int  catch_rec=builder.indexOf("}");
                    if (catch_rec>0)
                    {
                        String complete = builder.substring(0,catch_rec);
                        int complete_length = complete.length();
                        if (builder.charAt(0) == '{')
                        {
                            String finals = builder.substring(1,complete_length);
                            Log.d("test",finals);
                            textView.setText(finals);
                        }
                        builder.delete(0,builder.length());
                    }
                }
            }
        };
        radioGroup.setOnCheckedChangeListener(radioGroupcheckchange);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(getContext(), "bluetooth is opened", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "bluetooth no open", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
                break;
            case REQUEST_ENABLE_BT_connect:
                if (resultCode == Activity.RESULT_OK) {
                    MAC = data.getExtras().getString(ListStatus.trans_MAC);
                    //Toast.makeText(getApplicationContext()," connect" +  MAC,Toast.LENGTH_LONG).show();
                    mDrvice = BluetoothAdaper.getRemoteDevice(MAC);
                    try {
                        mSocket = mDrvice.createRfcommSocketToServiceRecord(uuid);
                        mSocket.connect();
                        mconnect = true;
                        btn_connect.setText("disconnect");
                        connectedThread = new ConnectedThread(mSocket);
                        connectedThread.start();
                        Toast.makeText(getContext(), " connect" + MAC, Toast.LENGTH_LONG).show();
                    } catch (IOException erro) {
                        mconnect = false;
                        Toast.makeText(getContext(), "error" + erro, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "fail connect", Toast.LENGTH_LONG).show();
                }
        }
    }



    private RadioGroup.OnCheckedChangeListener radioGroupcheckchange=new RadioGroup.OnCheckedChangeListener()
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            if(mconnect)
            {
                switch (checkedId)
                {
                    case R.id.rbt1:
                        connectedThread.write("a");
                        break;
                    case R.id.rbt2:
                        connectedThread.write("b");
                        break;
                    case R.id.rbt3:
                        connectedThread.write("c");
                        break;
                    case R.id.rbt4:
                        connectedThread.write("d");
                        break;
                    case R.id.rbt5:
                        connectedThread.write("e");
                        break;
                    case R.id.rbt6:
                        connectedThread.write("f");
                        break;
                }
            }
            else
            {
                Toast.makeText(getContext(),"bluetooth is not enable",Toast.LENGTH_LONG).show();
            }
        }
    };
}



