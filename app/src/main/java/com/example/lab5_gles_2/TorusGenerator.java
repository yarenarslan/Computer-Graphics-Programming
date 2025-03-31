package com.example.lab5_gles_2;

import java.util.ArrayList;
import java.util.List;

public class TorusGenerator {

    public static TorusData createTorus(float majorRadius, float minorRadius, int majorSegments, int minorSegments) {
        List<Float> vertices = new ArrayList<>();
        List<Float> texCoords = new ArrayList<>();
        List<Short> indices = new ArrayList<>();

        for (int i = 0; i <= majorSegments; i++) {
            float theta = (float) (2.0f * Math.PI * i / majorSegments);
            float cosTheta = (float) Math.cos(theta);
            float sinTheta = (float) Math.sin(theta);

            for (int j = 0; j <= minorSegments; j++) {
                float phi = (float) (2.0f * Math.PI * j / minorSegments);
                float cosPhi = (float) Math.cos(phi);
                float sinPhi = (float) Math.sin(phi);

                float x = (majorRadius + minorRadius * cosPhi) * cosTheta;
                float y = minorRadius * sinPhi;
                float z = (majorRadius + minorRadius * cosPhi) * sinTheta;

                float u = (float) i / majorSegments;
                float v = (float) j / minorSegments;

                vertices.add(x);
                vertices.add(y);
                vertices.add(z);

                texCoords.add(u);
                texCoords.add(v);
            }
        }

        int numVerticesMinor = minorSegments + 1;
        for (int i = 0; i < majorSegments; i++) {
            for (int j = 0; j < minorSegments; j++) {
                int first = i * numVerticesMinor + j;
                int second = first + numVerticesMinor;

                indices.add((short) first);
                indices.add((short) second);
                indices.add((short) (first + 1));

                indices.add((short) second);
                indices.add((short) (second + 1));
                indices.add((short) (first + 1));
            }
        }

        return new TorusData(
                toFloatArray(vertices),
                toFloatArray(texCoords),
                toShortArray(indices)
        );
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
