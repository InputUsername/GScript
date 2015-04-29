package com.inputusername.android.gscript.lang;

import com.inputusername.android.gscript.lang.types.GsObject;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Stack extends ArrayDeque<GsObject> {

    public GsObject pop() throws StackEmptyException {
        if (!isEmpty()) {
            return super.pop();
        }
        else {
            throw new StackEmptyException("Stack underflow");
        }
    }

    public GsObject peek() throws StackEmptyException {
        if (!isEmpty()) {
            return super.peek();
        }
        else {
            throw new StackEmptyException("Stack underflow");
        }
    }

}
