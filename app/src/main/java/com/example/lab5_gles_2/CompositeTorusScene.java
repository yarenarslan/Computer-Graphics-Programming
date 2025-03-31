
// CompositeTorusScene.java
package com.example.lab5_gles_2;

import android.content.Context;

public class CompositeTorusScene {

    private final TorusMode torusMode;
    private final TextureQuad innerQuad;

    public CompositeTorusScene(Context context) {
        torusMode = new TorusMode(context);
        innerQuad = new TextureQuad(context, R.drawable.torus_texture); // make sure this image exists
    }

    public void draw(float[] viewMatrix, float[] projectionMatrix) {
        torusMode.draw(viewMatrix, projectionMatrix);
        innerQuad.draw(viewMatrix, projectionMatrix);
    }
}
