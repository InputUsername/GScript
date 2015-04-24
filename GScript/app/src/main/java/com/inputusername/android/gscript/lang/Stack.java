package com.inputusername.android.gscript.lang;

import com.inputusername.android.gscript.lang.types.GsObject;

import java.util.ArrayDeque;

public class Stack extends ArrayDeque<GsObject> {

    @Override
    public GsObject pop() {
        if (!isEmpty()) {
            return super.pop();
        }
        else {
            //TODO: implement exceptions (stack empty)
        }
        return null;
    }

}
