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

    @Override
    public int compareTo(GsObject other) {
        if (Util.isSmaller(this, other)) {
            return -1;
        }
        else if (Util.areEqual(this, other)) {
            return 0;
        }
        else if (Util.isLarger(this, other)) {
            return 1;
        }

        return 1;
    }
}
