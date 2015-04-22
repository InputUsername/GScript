package com.inputusername.android.gscript.lang.types;

public class GsNumber implements GsObject {
    private int data;

    public GsNumber(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public String toString() {
        return Integer.toString(data);
    }

}
