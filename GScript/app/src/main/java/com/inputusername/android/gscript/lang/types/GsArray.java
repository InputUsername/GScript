package com.inputusername.android.gscript.lang.types;

import java.util.ArrayList;
import java.util.List;

public class GsArray implements GsObject {
    List<GsObject> data;

    public GsArray(List<GsObject> objects) {
        data = new ArrayList<>();
        for (GsObject object : objects) {
            data.add(object);
        }
    }

    public GsObject get(int index) {
        return data.get(index);
    }

    public void set(int index, GsObject object) {
        data.set(index, object);
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");
        int size = data.size();

        for (int i = 0; i < size; ++i) {
            result.append(data.get(i).toString());
            result.append(" ");
        }
        result.replace(size - 1, size, "]");

        return result.toString();
    }
}
