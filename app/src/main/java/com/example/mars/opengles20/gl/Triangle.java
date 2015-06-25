package com.example.mars.opengles20.gl;

import android.graphics.PointF;
import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by mars on 6/24/15.
 */
public class Triangle {
    private float vertices[];
//    =
//            {       10.0f, 200f, 0.0f,
//                    10.0f, 100f, 0.0f,
//                    100f, 100f, 0.0f,
//            };
    public short idx[]={0, 1, 2};
    public FloatBuffer vBuf;
    public ShortBuffer drawLBuf;

    public Triangle(PointF origin, float width, float height) {
        vertices = new float[]{
                origin.x, origin.y+height, 0f,
                origin.x, origin.y, 0f,
                origin.x+width, origin.y, 0f
        };

        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vBuf = bb.asFloatBuffer();
        vBuf.put(vertices);
        vBuf.position(0);

        ByteBuffer dlb = ByteBuffer.allocateDirect(idx.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawLBuf = dlb.asShortBuffer();
        drawLBuf.put(idx);
        drawLBuf.position(0);
    }

    public void render(float[] m) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // get handle to vertex shader's vPosition member
        int mPositionHandle = GLES20.glGetAttribLocation(Shaders.program, "vPosition");

        // Enable generic vertex attribute array
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, 3,
                GLES20.GL_FLOAT, false,
                0, vBuf);

        // Get handle to shape's transformation matrix
        int mtrxhandle = GLES20.glGetUniformLocation(Shaders.program, "uMVPMatrix");

        // Apply the projM and view transformation
        GLES20.glUniformMatrix4fv(mtrxhandle, 1, false, m, 0);

        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, idx.length,
                GLES20.GL_UNSIGNED_SHORT, drawLBuf);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);

    }
}
