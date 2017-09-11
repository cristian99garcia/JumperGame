package com.zades.jumper.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.zades.jumper.JumperGame;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        //config.useAccelerometer = false;
        //config.useCompass = false;

        config.useImmersiveMode = true;
        initialize(new JumperGame(), config);
    }
}
