package com.example.petstorerestapi;


import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.appspector.sdk.AppSpector;
import com.appspector.sdk.monitors.commands.CommandCallback;
import com.appspector.sdk.monitors.commands.Responder;


public class RestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppSpector.build(this)
                .withDefaultMonitors()
                .run("android_ZjNjNzFjOTAtMmI4My00ZGUxLTk1ZmYtNjI5NGFiN2U0ZTdm");

        AppSpector.shared().commands().register(ShowToastCommand.class, new CommandCallback<Integer, ShowToastCommand>() {
            @Override
            public void exec(ShowToastCommand showToastCommand, Responder<Integer> responder) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), showToastCommand.message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



}
