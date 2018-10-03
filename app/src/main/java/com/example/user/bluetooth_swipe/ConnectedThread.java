package com.example.user.bluetooth_swipe;

import android.bluetooth.BluetoothSocket;
import android.os.Handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.user.bluetooth_swipe.bluetooth.mSocket;

public  class ConnectedThread extends Thread {
    //BluetoothSocket mSocket = null;
    private  static final int MESSAGE_READ=3;
    Handler mHandler;

    private final InputStream mmInStream;
    private final OutputStream mmOutStream;
    private byte[] mmBuffer; // mmBuffer store for the stream

    public  ConnectedThread(BluetoothSocket socket) {
        mSocket = socket;
        InputStream tmpIn = null;
        OutputStream tmpOut = null;
        try {
            tmpIn = socket.getInputStream();
        } catch (IOException e) {
        }
        try {
            tmpOut = socket.getOutputStream();
        } catch (IOException e) {
        }
        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run() {
        mmBuffer = new byte[1024];
        int numBytes; // bytes returned from read()

        // Keep listening to the InputStream until an exception occurs.
        while (true) {
            try {
                // Read from the InputStream.
                numBytes = mmInStream.read(mmBuffer);
                String bt = new String(mmBuffer,0,numBytes);
                mHandler.obtainMessage(MESSAGE_READ, numBytes, -1,bt).sendToTarget();
            } catch (IOException e) {
                break;
            }
        }
    }

    // Call this from the main activity to send data to the remote device.
    public void write(String data) {
        byte []magBuffer=data.getBytes();
        try {
            mmOutStream.write(magBuffer);
        } catch (IOException e) {
        }
    }
}