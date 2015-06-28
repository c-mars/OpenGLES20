package com.example.mars.opengles20.gl;

import android.opengl.GLES20;

/**
 * Created by Constantine Mars on 6/25/15.
 */
public class Circle {
    private float[] verts;
    public Circle(float rad, int points, float x, float y)
    {
        verts=new float[points*2+2];
        boolean first=true;
        float fx=0;
        float fy=0;
        int c=0;
        for (int i = 0; i < points; i++)
        {
            float fi = (float) (2*Math.PI*i/points);
            float xa = (float) (rad*Math.sin(fi + Math.PI)+x);
            float ya = (float) (rad*Math.cos(fi + Math.PI)+y);
            if(first)
            {
                first=false;
                fx=xa;
                fy=ya;
            }
            verts[c]=xa;
            verts[c+1]=ya;
            c+=2;
        }
        verts[c]=fx;
        verts[c+1]=fy;
    }

    public void render(){
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, verts.length / 2);
    }

}
