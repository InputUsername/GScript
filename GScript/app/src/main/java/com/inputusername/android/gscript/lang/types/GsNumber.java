package com.inputusername.android.gscript.lang.types;

import com.inputusername.android.gscript.lang.Util;

public class GsNumber implements GsObject {
    private int data;

    public GsNumber(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public String toString() {
        return Util.escape(Integer.toString(data));
    }

}
