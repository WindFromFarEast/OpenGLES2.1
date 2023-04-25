package com.example.myapplication.util;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.Buffer;

public class GLUtil {

    private static final String TAG = "GLUtil";

    public static void glViewPort(int x, int y, int width, int height) {
        GLES20.glViewport(x, y, width, height);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glViewPort failed. error is " + error);
        }
    }

    public static void glClearColor(float r, float g, float b, float a) {
        GLES20.glClearColor(r, g, b, a);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glClearColor failed. error is " + error);
        }
    }

    public static void glClear(int mask) {
        GLES20.glClear(mask);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glClear failed. error is " + error);
        }
    }

    public static int glCreateShader(int type) {
        int id = GLES20.glCreateShader(type);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glCreateShader failed. error is " + error);
        }
        return id;
    }

    public static void glShaderSource(int shader, String code) {
        GLES20.glShaderSource(shader, code);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glShaderSource failed. error is " + error);
        }
    }

    public static void glCompileShader(int shader) {
        GLES20.glCompileShader(shader);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glCompileShader failed. error is " + error);
        }
    }

    public static void glGetShaderiv(int shader, int pname, int[] params, int offset) {
        GLES20.glGetShaderiv(shader, pname, params, offset);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glGetShaderiv failed. error is " + error);
        }
    }

    public static String glGetShaderInfoLog(int shader) {
        String info = GLES20.glGetShaderInfoLog(shader);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glGetShaderInfoLog failed. error is " + error);
        }
        return info;
    }

    public static void glDeleteShader(int shader) {
        GLES20.glDeleteShader(shader);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glDeleteShader failed. error is " + error);
        }
    }

    public static int glCreateProgram() {
        int id = GLES20.glCreateProgram();
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glCreateProgram failed. error is " + error);
        }
        return id;
    }

    public static void glAttachShader(int program, int shader) {
        GLES20.glAttachShader(program, shader);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glAttachShader failed. error is " + error);
        }
    }

    public static void glLinkProgram(int program) {
        GLES20.glLinkProgram(program);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glLinkProgram failed. error is " + error);
        }
    }

    public static void glGetProgramiv(int program, int pname, int[] params, int offset) {
        GLES20.glGetProgramiv(program, pname, params, offset);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glGetProgramiv failed. error is " + error);
        }
    }

    public static void glDeleteProgram(int program) {
        GLES20.glDeleteProgram(program);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glDeleteProgram failed. error is " + error);
        }
    }

    public static void glValidateProgram(int program) {
        GLES20.glValidateProgram(program);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glValidateProgram failed. error is " + error);
        }
    }

    public static void glUseProgram(int program) {
        GLES20.glUseProgram(program);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glUseProgram failed. error is " + error);
        }
    }

    public static int glGetUniformLocation(int program, String name) {
        int location = GLES20.glGetUniformLocation(program, name);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glGetUniformLocation failed. error is " + error);
        }
        return location;
    }

    public static int glGetAttribLocation(int program, String name) {
        int location = GLES20.glGetAttribLocation(program, name);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glGetAttribLocation failed. error is " + error);
        }
        return location;
    }

    public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, Buffer buffer) {
        GLES20.glVertexAttribPointer(index, size, type, normalized, stride, buffer);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glVertexAttribPointer failed. error is " + error);
        }
    }

    public static void glEnableVertexAttribArray(int index) {
        GLES20.glEnableVertexAttribArray(index);
        int error;
        if ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, "glEnableVertexAttribArray failed. error is " + error);
        }
    }

    public static void glUniform4f(int location, float x, float y, float z, float w) {
        GLES20.glUniform4f(location, x, y, z, w);
    }

    public static void glDrawArrays(int mode, int first, int count) {
        GLES20.glDrawArrays(mode, first, count);
    }

}
