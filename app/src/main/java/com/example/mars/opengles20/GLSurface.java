package com.example.mars.opengles20;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by mars on 6/22/15.
 */
public class GLSurface extends GLSurfaceView {

    private GLRenderer mRenderer;

    public GLSurface(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new GLRenderer(context);
        setRenderer(mRenderer);

        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY); // fixme RENDERMODE_WHENDIRTY
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mRenderer.onPause();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mRenderer.onResume();
    }
}
