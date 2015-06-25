package com.example.mars.opengles20.gl;

import android.opengl.GLES20;

/**
 * Created by Constantine Mars on 6/22/15.
 */
public class Shaders {
    public static int program;

    private static final String VERTEX_SHADER =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private static final String FRAGMENT_SHADER =
            "precision mediump float;" +
                    "void main() {" +
                    "  gl_FragColor = vec4(0,1,0,1);" +
                    "}";

    private static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public Shaders() {
        int vertexShader = Shaders.loadShader(GLES20.GL_VERTEX_SHADER, Shaders.VERTEX_SHADER);
        int fragmentShader = Shaders.loadShader(GLES20.GL_FRAGMENT_SHADER, Shaders.FRAGMENT_SHADER);

        Shaders.program = GLES20.glCreateProgram();
        GLES20.glAttachShader(Shaders.program, vertexShader);
        GLES20.glAttachShader(Shaders.program, fragmentShader);
        GLES20.glLinkProgram(Shaders.program);
        GLES20.glUseProgram(Shaders.program);
    }
}
