package com.example.mars.opengles20.gl;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Build;
import android.util.SizeF;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Constantine Mars on 6/22/15.
 */
public class Renderer implements GLSurfaceView.Renderer {

    private final float[] projM = new float[16];
    private final float[] viewM = new float[16];
    private final float[] projViewM = new float[16];

    private SizeF size;
    private Context c;

    private Shaders shaders;
    private Triangle triangle;
    private Circle circle;

    public Renderer(Context c) {
        this.c = c;
    }

    public void onPause() {}

    public void onResume() {}

    @Override
    public void onDrawFrame(GL10 unused) {
//        if(triangle==null && size!=null){ createTriangle(); }
//        if(triangle!=null) {triangle.render(projViewM);}
        circle.render();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        size=new SizeF(width, height);

        GLES20.glViewport(0, 0, (int)size.getWidth(), (int)size.getHeight());

        for (int i = 0; i < 16; i++) {
            projM[i] = 0.0f;
            viewM[i] = 0.0f;
            projViewM[i] = 0.0f;
        }

        Matrix.orthoM(projM, 0, 0f, size.getWidth(), 0.0f, size.getHeight(), 0, 50);
        Matrix.setLookAtM(viewM, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.multiplyMM(projViewM, 0, projM, 0, viewM, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        if(size!=null) {
            createTriangle();
        }
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1);
        shaders=new Shaders();
    }

    private void createTriangle(){
            triangle = new Triangle(new PointF(size.getWidth()/ 10f, size.getHeight() / 10f), size.getWidth()* 0.8f, size.getHeight()* 0.8f);
            float r= (float) (size.getWidth()*0.4);
            circle=new Circle(r, 6, (float)(size.getWidth()*0.1), (float)(size.getHeight()*0.1));
    }
}
