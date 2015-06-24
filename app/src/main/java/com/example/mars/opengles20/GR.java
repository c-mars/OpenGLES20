package com.example.mars.opengles20;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by mars on 6/22/15.
 */
public class GR implements GLSurfaceView.Renderer {

    private final float[] projM = new float[16];
    private final float[] viewM = new float[16];
    private final float[] projViewM = new float[16];

    // Our screenresolution
    float sw = 100;// 1280;
    float sh =100;// 768;

    // Misc
    Context c;
    long lTime;
    int p;

    public GR(Context c){
        this.c = c;
        lTime = System.currentTimeMillis() + 100;
    }

    public void onPause() {
        /* Do stuff to pause the renderer */
    }

    public void onResume() {
        /* Do stuff to resume the renderer */
        lTime = System.currentTimeMillis();
    }

    @Override
    public void onDrawFrame(GL10 unused) {

        // Get the current time
        long now = System.currentTimeMillis();

        // We should make sure we are valid and sane
        if (lTime > now) return;

        // Get the amount of time the last frame took.
        long elapsed = now - lTime;

        // Update our example

        // Render our example
        Render(projViewM);

        // Save the current time to see how long it took <img src="http://androidblog.reindustries.com/wp-includes/images/smilies/icon_smile.gif" alt=":)" class="wp-smiley"> .
        lTime = now;

    }

    private void Render(float[] m) {

        // clear Screen and Depth Buffer, we have set the clear color as black.
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // get handle to vertex shader's vPosition member
        int mPositionHandle = GLES20.glGetAttribLocation(riGraphicTools.sp_SolidColor, "vPosition");

        // Enable generic vertex attribute array
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, 3,
                GLES20.GL_FLOAT, false,
                0, tri.vBuf);

        // Get handle to shape's transformation matrix
        int mtrxhandle = GLES20.glGetUniformLocation(riGraphicTools.sp_SolidColor, "uMVPMatrix");

        // Apply the projM and view transformation
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, m, 0);

        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, tri.idx.length,
                GLES20.GL_UNSIGNED_SHORT, tri.drawLBuf);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        // We need to know the current width and height.
        sw = width;
        sh = height;

        // Redo the Viewport, making it fullscreen.
        GLES20.glViewport(0, 0, (int) sw, (int) sh);

        // Clear our matrices
        for(int i=0;i<16;i++)
        {
            projM[i] = 0.0f;
            viewM[i] = 0.0f;
            projViewM[i] = 0.0f;
        }

        // Setup our screen width and height for normal sprite translation.
        Matrix.orthoM(projM, 0, 0f, sw, 0.0f, sh, 0, 50);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewM, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projM and view transformation
        Matrix.multiplyMM(projViewM, 0, projM, 0, viewM, 0);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        // Create the triangle
        tri=new Tri();

        // Set the clear color to black
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1);

        // Create the shaders
        int vertexShader = riGraphicTools.loadShader(GLES20.GL_VERTEX_SHADER, riGraphicTools.vs_SolidColor);
        int fragmentShader = riGraphicTools.loadShader(GLES20.GL_FRAGMENT_SHADER, riGraphicTools.fs_SolidColor);

        riGraphicTools.sp_SolidColor = GLES20.glCreateProgram();             // create empty OpenGL ES Program
        GLES20.glAttachShader(riGraphicTools.sp_SolidColor, vertexShader);   // add the vertex shader to program
        GLES20.glAttachShader(riGraphicTools.sp_SolidColor, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(riGraphicTools.sp_SolidColor);                  // creates OpenGL ES program executables

        // Set our shader programm
        GLES20.glUseProgram(riGraphicTools.sp_SolidColor);
    }

    private Tri tri;
}
