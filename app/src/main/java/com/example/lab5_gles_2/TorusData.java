package com.example.lab5_gles_2;

public class TorusData {
    public float[] vertices;
    public float[] texCoords;
    public short[] indices;

    public TorusData(float[] vertices, float[] texCoords, short[] indices) {
        this.vertices = vertices;
        this.texCoords = texCoords;
        this.indices = indices;
    }
}
