package com.example.user.bluetooth_swipe;


import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Set;

public class ListStatus extends ListActivity{

    private BluetoothAdapter meuBluetoothAdapter2=null;

    static String trans_MAC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> ArrayBluetooth = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        meuBluetoothAdapter2 = BluetoothAdapter.getDefaultAdapter();

        Set<BluetoothDevice> btdevice = meuBluetoothAdapter2.getBondedDevices();

        if ((btdevice.size()>0))
        {
            for (BluetoothDevice btcatch:btdevice)
            {
                String name = btcatch.getName();
                String address = btcatch.getAddress();
                ArrayBluetooth.add(name+"\n"+address);
            }
        }
        setListAdapter(ArrayBluetooth);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String information = ((TextView) v).getText().toString();

        // Toast.makeText(getApplicationContext(),"info"+information,Toast.LENGTH_LONG).show();

        String BTMAC =information.substring(information.length()-17);
        //Toast.makeText(getApplicationContext(),"mac"+BTMAC,Toast.LENGTH_LONG).show();

        Intent GETMAC = new Intent();
        GETMAC.putExtra(trans_MAC,BTMAC);
        setResult(RESULT_OK,GETMAC);
        finish();
    }
}
