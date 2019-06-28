package com.example.kitapgunlugum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.pusher.pushnotifications.PushNotifications;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushNotifications.start(getApplicationContext(), "a7bc7826-c953-4cbd-8afa-4451d01473e2");
        PushNotifications.addDeviceInterest("hello");
    }

}


