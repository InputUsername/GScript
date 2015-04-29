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
