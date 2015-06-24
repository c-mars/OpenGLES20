package com.example.mars.opengles20;

import android.opengl.GLSurfaceView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {

    private GLSurfaceView gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rl= (RelativeLayout) findViewById(R.id.gamelayout);
        gs =new GS(this, rl);
    }

    @Override
    protected void onPause() {
        gs.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gs.onResume();
    }
}
