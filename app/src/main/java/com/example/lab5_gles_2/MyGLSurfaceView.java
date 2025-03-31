
// MyGLSurfaceView.java (güncellenmiş)
package com.example.lab5_gles_2;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MyGLSurfaceView extends GLSurfaceView {
    private final MyGLRenderer renderer;
    private float previousX, previousY, previousDistance;

    public MyGLSurfaceView(Context context, String mode) {
        super(context);
        setEGLContextClientVersion(2);
        renderer = new MyGLRenderer(context, mode);
        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        switch (e.getPointerCount()) {
            case 1:
                if (e.getAction() == MotionEvent.ACTION_MOVE) {
                    float dx = x - previousX;
                    float dy = y - previousY;
                    renderer.setAngleDelta(dx, dy);
                }
                previousX = x;
                previousY = y;
                break;
            case 2:
                if (e.getActionMasked() == MotionEvent.ACTION_MOVE) {
                    float dx = e.getX(0) - e.getX(1);
                    float dy = e.getY(0) - e.getY(1);
                    float distance = (float) Math.sqrt(dx * dx + dy * dy);
                    if (previousDistance != 0f) {
                        float scale = distance - previousDistance;
                        renderer.setZoomDelta(scale / 50f);
                    }
                    previousDistance = distance;
                } else if (e.getActionMasked() == MotionEvent.ACTION_POINTER_UP) {
                    previousDistance = 0f;
                }
                break;
        }
        return true;
    }
}

