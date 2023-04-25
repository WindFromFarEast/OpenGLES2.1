package com.example.myapplication.util;

import android.opengl.GLES20;
import android.opengl.GLU;
import android.util.Log;

public class ShaderHelper {

    private static final String TAG = "ShaderHelper";

    public static int compileVertexShader(String shaderCode) {
        return compileShader(GLES20.GL_VERTEX_SHADER, shaderCode);
    }

    public static int compileFragmentShader(String shaderCode) {
        return compileShader(GLES20.GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String shaderCode) {
        int shaderObjectId = GLUtil.glCreateShader(type);
        if (shaderObjectId == 0) {
            throw new RuntimeException("Create shader failed!");
        }

        GLUtil.glShaderSource(shaderObjectId, shaderCode);

        GLUtil.glCompileShader(shaderObjectId);
        final int[] compileStatus = new int[1];
        GLUtil.glGetShaderiv(shaderObjectId, GLES20.GL_COMPILE_STATUS, compileStatus, 0);
        Log.d(TAG, "Result of compile shader source: " + "\n" + shaderCode + "\n" + GLUtil.glGetShaderInfoLog(shaderObjectId));
        if (compileStatus[0] == 0) {
            GLUtil.glDeleteShader(shaderObjectId);
            throw new RuntimeException("Compile shader failed!");
        }

        return shaderObjectId;
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {
        int programObjectId = GLUtil.glCreateProgram();
        if (programObjectId == 0) {
            throw new RuntimeException("Create program failed!");
        }

        GLUtil.glAttachShader(programObjectId, fragmentShaderId);
        GLUtil.glAttachShader(programObjectId, vertexShaderId);

        GLUtil.glLinkProgram(programObjectId);
        final int[] linkStatus = new int[1];
        GLUtil.glGetProgramiv(programObjectId, GLES20.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] == 0) {
            GLUtil.glDeleteProgram(programObjectId);
            throw new RuntimeException("Link program failed!");
        }

        return programObjectId;
    }

    public static boolean validateProgram(int program) {
        GLUtil.glValidateProgram(program);
        int[] validateStatus = new int[1];
        GLUtil.glGetProgramiv(program, GLES20.GL_VALIDATE_STATUS, validateStatus, 0);
        return validateStatus[0] != 0;
    }



}
