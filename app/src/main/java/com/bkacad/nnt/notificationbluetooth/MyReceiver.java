package com.bkacad.nnt.notificationbluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public abstract class MyReceiver extends BroadcastReceiver {

    public abstract void bluetoothOn();

    public abstract void bluetoothOff();

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        bluetoothOff();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        bluetoothOn();
                        break;
                }
        }
    }
}
