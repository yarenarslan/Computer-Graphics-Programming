// SceneActivity.java
package com.example.lab5_gles_2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.opengl.GLSurfaceView;

public class SceneActivity extends AppCompatActivity {
    private MyGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String mode = getIntent().getStringExtra("mode");
        glSurfaceView = new MyGLSurfaceView(this, mode);
        setContentView(glSurfaceView);
    }
}
