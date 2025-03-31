package com.example.lab5_gles_2;

import android.content.Context;
import android.opengl.GLES20;

public class SeaviewMode {
    private final Skybox skybox;
    private final TorusMode torus;
    private final ChessboardQuad chessboard;

    public SeaviewMode(Context context) {
        skybox = new Skybox(context); // gökyüzü küpü
        torus = new TorusMode(context); // torus nesnesi
        chessboard = new ChessboardQuad(context, R.drawable.torus_texture); // satranç düzlemi
    }

    public void draw(float[] viewMatrix, float[] projectionMatrix) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Skybox ilk çizilir, çünkü uzak nesnedir
        skybox.draw(viewMatrix, projectionMatrix);

        // Ardından zemin (chessboard) ve torus çizilir
        chessboard.draw(viewMatrix, projectionMatrix);
        torus.draw(viewMatrix, projectionMatrix);
    }
} // EOF
