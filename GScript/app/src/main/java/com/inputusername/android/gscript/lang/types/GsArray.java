package com.inputusername.android.gscript.lang.types;

import com.inputusername.android.gscript.lang.Util;

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

    public List<GsObject> getData() {
        return data;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");
        int size = data.size();

        for (int i = 0; i < size; ++i) {
            result.append(data.get(i).toString());
            result.append(" ");
        }

        int resultLength = result.length();
        result.replace(resultLength - 1, resultLength, "]");

        return result.toString();
    }
}
