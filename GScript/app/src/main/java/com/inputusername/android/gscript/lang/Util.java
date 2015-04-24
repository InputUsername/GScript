package com.inputusername.android.gscript.lang;

import com.inputusername.android.gscript.lang.types.GsArray;
import com.inputusername.android.gscript.lang.types.GsBlock;
import com.inputusername.android.gscript.lang.types.GsNumber;
import com.inputusername.android.gscript.lang.types.GsObject;
import com.inputusername.android.gscript.lang.types.GsString;

public class Util {
    public static boolean truthy(GsObject object) {
        boolean truthy = true;

        if (object instanceof GsNumber) {
            GsNumber number = (GsNumber)object;
            if (number.getData() == 0) {
                truthy = false;
            }
        }
        else if (object instanceof GsString) {
            GsString string = (GsString)object;
            if (string.getData().isEmpty()) {
                truthy = false;
            }
        }
        else if (object instanceof GsBlock) {
            GsBlock block = (GsBlock)object;
            if (block.getData().isEmpty()) {
                truthy = false;
            }
        }
        else if (object instanceof GsArray) {
            GsArray array = (GsArray)object;
            if (array.getData().isEmpty()) {
                truthy = false;
            }
        }

        return truthy;
    }

    public static boolean isNumber(GsObject object) {
        return (object instanceof GsNumber);
    }

    public static boolean isString(GsObject object) {
        return (object instanceof GsString);
    }

    public static boolean isBlock(GsObject object) {
        return (object instanceof GsBlock);
    }

    public static boolean isArray(GsObject object) {
        return (object instanceof GsArray);
    }
}
