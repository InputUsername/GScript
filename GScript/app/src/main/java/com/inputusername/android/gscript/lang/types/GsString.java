package com.inputusername.android.gscript.lang.types;

public class GsString implements GsObject {
    private String data;

    public GsString(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String toString() {
        return "\"" + data + "\"";
    }
}
