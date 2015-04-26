package com.inputusername.android.gscript.lang.types;

import com.inputusername.android.gscript.lang.Util;

public class GsBlock implements GsObject {
    private String data;

    public GsBlock(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String toString() {
        return "{" + Util.escape(data) + "}";
    }
}
