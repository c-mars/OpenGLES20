package com.example.mars.opengles20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by mars on 6/24/15.
 */
public class Tri {
    private float v[] =
            {       10.0f, 200f, 0.0f,
                    10.0f, 100f, 0.0f,
                    100f, 100f, 0.0f,
            };
    public short idx[];
    public FloatBuffer vBuf;
    public ShortBuffer drawLBuf;

    public Tri() {
        idx = new short[] {0, 1, 2}; // loop in the android official tutorial opengles why different order.


        // The vertex buffer.
        ByteBuffer bb = ByteBuffer.allocateDirect(v.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vBuf = bb.asFloatBuffer();
        vBuf.put(v);
        vBuf.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(idx.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawLBuf = dlb.asShortBuffer();
        drawLBuf.put(idx);
        drawLBuf.position(0);
    }
}
