package com.example.mars.opengles20;

import android.opengl.GLSurfaceView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.mars.opengles20.gl.Surface;


public class MainActivity extends ActionBarActivity {

    private GLSurfaceView gs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rl= (RelativeLayout) findViewById(R.id.gamelayout);
        gs =new Surface(this, rl);
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
