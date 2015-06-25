package com.example.mars.opengles20.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Constantine Mars on 6/22/15.
 */
public class Surface extends GLSurfaceView {

    private com.example.mars.opengles20.gl.Renderer r;

    public Surface(Context c, ViewGroup vg) {
        super(c);
        setEGLContextClientVersion(2);

        r = new com.example.mars.opengles20.gl.Renderer(c);
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
