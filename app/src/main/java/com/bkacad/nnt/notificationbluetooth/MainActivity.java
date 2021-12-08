package com.bkacad.nnt.notificationbluetooth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.bluetooth.BluetoothAdapter;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  MyReceiver myReceiver;
    private IntentFilter intentFilter;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);

        myReceiver = new MyReceiver() {
            @Override
            public void bluetoothOn() {
                notificationManagerCompat.cancel(1);

                Toast.makeText(MainActivity.this,"Bật bluetooth",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void bluetoothOff() {
                // Hiển thị notification
                Notification notification = new NotificationCompat.Builder(MainActivity.this,"channel1")
                        .setSmallIcon(R.drawable.ic_baseline_info_24)
                        .setContentText("Ứng dụng không hoạt động nếu tắt bluetooth")
                        .setContentTitle("My Application")
                        .setPriority(Notification.PRIORITY_HIGH)
                        .build();

                notificationManagerCompat.notify(1, notification);


                Toast.makeText(MainActivity.this,"Tắt bluetooth",Toast.LENGTH_SHORT).show();
            }
        };

    }
    // Đăng kí lắng nghe sự kiện bật hoặc tắt

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReceiver, intentFilter);
    }

    // Huỷ đăng kí receiver khi pause

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }
}