package com.example.myapplication;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.example.myapplication.util.GLUtil;
import com.example.myapplication.util.ShaderHelper;
import com.example.myapplication.util.TextResourceReader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class AirHockeyRenderer implements GLSurfaceView.Renderer {

    private static final int POSITION_COMPONENT_COUNT = 2;//每个顶点由两个分量表示
    private static final int BYTES_PER_FLOAT = 4;//每个浮点数4个字节
    private final FloatBuffer vertexData;//存放顶点坐标数组
    private Context context;
    private int program;
    private static final String U_COLOR = "u_Color";
    private static final String A_POSITION = "a_Position";
    private int aPositionLocation;
    private static final String A_COLOR = "a_Color";
    private static final int COLOR_COMPONENT_COUT = 3;
    private static final int STRIDE = (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUT) * BYTES_PER_FLOAT;
    private int aColorLocation;

    public AirHockeyRenderer(Context context) {
        this.context = context;

        float[] tableVerticesWithTriangles = {
                //Triangle Fan
                0f, 0f, 1f, 1f, 1f,
                -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
                0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
                0.5f, 0.5f, 0.7f, 0.7f, 0.7f,
                -0.5f, 0.5f, 0.7f, 0.7f, 0.7f,
                -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
                //Line 1
                -0.5f, 0f, 1f, 0f, 0f,
                0.5f, 0f, 1f, 0f, 0f,
                //Mallets
                0f, -0.25f, 0f, 0f, 1f,
                0f, 0.25f, 0f, 0f, 1f,
        };

        vertexData = ByteBuffer
                .allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexData.put(tableVerticesWithTriangles);
        vertexData.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLUtil.glClearColor(0.0f, 0f, 0f, 0f);
        String vertexShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_vertex_shader);
        String fragmentShaderSource = TextResourceReader.readTextFileFromResource(context, R.raw.simple_fragment_shader);
        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderSource);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderSource);
        program = ShaderHelper.linkProgram(vertexShader, fragmentShader);
        if (!ShaderHelper.validateProgram(program)) {
            throw new RuntimeException("validate program failed!");
        }
        GLUtil.glUseProgram(program);
        aPositionLocation = GLUtil.glGetAttribLocation(program, A_POSITION);
        GLUtil.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES20.GL_FLOAT, false, STRIDE, vertexData);
        GLUtil.glEnableVertexAttribArray(aPositionLocation);
        aColorLocation = GLUtil.glGetAttribLocation(program, A_COLOR);
        vertexData.position(POSITION_COMPONENT_COUNT);
        GLUtil.glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUT, GLES20.GL_FLOAT, false, STRIDE, vertexData);
        GLUtil.glEnableVertexAttribArray(aColorLocation);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLUtil.glViewPort(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLUtil.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        GLUtil.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);
        GLUtil.glDrawArrays(GLES20.GL_LINES, 6, 2);
        GLUtil.glDrawArrays(GLES20.GL_POINTS, 8, 1);
        GLUtil.glDrawArrays(GLES20.GL_POINTS, 9, 1);
    }

}
