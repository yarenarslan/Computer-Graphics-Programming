
// SphereGenerator.java
package com.example.lab5_gles_2;

import java.util.ArrayList;
import java.util.List;

public class SphereGenerator {
    public static SphereData createSphere(int stacks, int slices, float radius) {
        List<Float> vertexList = new ArrayList<>();
        List<Float> texCoordList = new ArrayList<>();
        List<Float> normalList = new ArrayList<>();
        List<Short> indexList = new ArrayList<>();

        for (int i = 0; i <= stacks; i++) {
            double phi = Math.PI * i / stacks;
            float y = (float) Math.cos(phi);
            float r = (float) Math.sin(phi);

            for (int j = 0; j <= slices; j++) {
                double theta = 2.0 * Math.PI * j / slices;
                float x = (float) (r * Math.cos(theta));
                float z = (float) (r * Math.sin(theta));

                vertexList.add(radius * x);
                vertexList.add(radius * y);
                vertexList.add(radius * z);

                normalList.add(x);
                normalList.add(y);
                normalList.add(z);

                texCoordList.add((float) j / slices);
                texCoordList.add(1f - ((float) i / stacks));
            }
        }

        for (int i = 0; i < stacks; i++) {
            for (int j = 0; j < slices; j++) {
                short first = (short) (i * (slices + 1) + j);
                short second = (short) (first + slices + 1);

                indexList.add(first);
                indexList.add(second);
                indexList.add((short) (first + 1));

                indexList.add(second);
                indexList.add((short) (second + 1));
                indexList.add((short) (first + 1));
            }
        }

        float[] vertices = toFloatArray(vertexList);
        float[] texCoords = toFloatArray(texCoordList);
        float[] normals = toFloatArray(normalList);
        short[] indices = toShortArray(indexList);

        return new SphereData(vertices, texCoords, normals, indices);
    }

    private static float[] toFloatArray(List<Float> list) {
        float[] array = new float[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private static short[] toShortArray(List<Short> list) {
        short[] array = new short[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
