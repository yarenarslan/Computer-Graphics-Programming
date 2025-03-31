// SphereData.java
package com.example.lab5_gles_2;

public class SphereData {
    public float[] vertices;
    public float[] texCoords;
    public float[] normals;
    public short[] indices;

    public SphereData(float[] vertices, float[] texCoords, float[] normals, short[] indices) {
        this.vertices = vertices;
        this.texCoords = texCoords;
        this.normals = normals;
        this.indices = indices;
    }
}
