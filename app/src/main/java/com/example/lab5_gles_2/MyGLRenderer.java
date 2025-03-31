// MyGLRenderer.java (mode seçimi ile)
package com.example.lab5_gles_2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyGLRenderer implements GLSurfaceView.Renderer {
    private final Context context;
    private final String mode;

    private float angleX = 0f;
    private float angleY = 0f;
    private float distance = 4f;

    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    private TorusMode torusMode;
    private EarthMode earthMode;
    private SeaviewMode seaviewMode;

    public MyGLRenderer(Context context, String mode) {
        this.context = context;
        this.mode = mode;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0f, 0f, 0f, 1f);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        if (mode.equals("torus")) torusMode = new TorusMode(context);
        else if (mode.equals("earth")) earthMode = new EarthMode(context);
        else if (mode.equals("seaview")) seaviewMode = new SeaviewMode(context);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 2, 10);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        float[] eye = {
                (float)(distance * Math.sin(Math.toRadians(angleX)) * Math.cos(Math.toRadians(angleY))),
                (float)(distance * Math.sin(Math.toRadians(angleY))),
                (float)(distance * Math.cos(Math.toRadians(angleX)) * Math.cos(Math.toRadians(angleY)))
        };

        Matrix.setLookAtM(mViewMatrix, 0, eye[0], eye[1], eye[2], 0f, 0f, 0f, 0f, 1f, 0f);

        if (mode.equals("torus") && torusMode != null) torusMode.draw(mViewMatrix, mProjectionMatrix);
        else if (mode.equals("earth") && earthMode != null) earthMode.draw(mViewMatrix, mProjectionMatrix);
        else if (mode.equals("seaview") && seaviewMode != null) seaviewMode.draw(mViewMatrix, mProjectionMatrix);
    }

    public void setAngleDelta(float dx, float dy) {
        angleX += dx * 0.5f;
        angleY += dy * 0.5f;
        angleY = Math.max(-89f, Math.min(89f, angleY));
    }

    public void setZoomDelta(float delta) {
        distance -= delta;
        distance = Math.max(2f, Math.min(10f, distance));
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
