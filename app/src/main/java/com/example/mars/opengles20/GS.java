package com.example.mars.opengles20;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by mars on 6/22/15.
 */
public class GS extends GLSurfaceView {

    private GR r;

    public GS(Context c, ViewGroup vg) {
        super(c);
        setEGLContextClientVersion(2);

        r = new GR(c);
        setRenderer(r);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        vg.addView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        r.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        r.onResume();
    }
}
