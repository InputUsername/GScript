package com.inputusername.android.gscript.lang;

import com.inputusername.android.gscript.lang.types.GsArray;
import com.inputusername.android.gscript.lang.types.GsBlock;
import com.inputusername.android.gscript.lang.types.GsNumber;
import com.inputusername.android.gscript.lang.types.GsObject;
import com.inputusername.android.gscript.lang.types.GsString;

import java.util.List;

public class Util {
    public static boolean truthy(GsObject object) {
        boolean truthy = true;

        if (isNumber(object)) {
            GsNumber number = (GsNumber)object;
            if (number.getData() == 0) {
                truthy = false;
            }
        }
        else if (isString(object)) {
            GsString string = (GsString)object;
            if (string.getData().isEmpty()) {
                truthy = false;
            }
        }
        else if (isBlock(object)) {
            GsBlock block = (GsBlock)object;
            if (block.getData().isEmpty()) {
                truthy = false;
            }
        }
        else if (isArray(object)) {
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

    public static GsObject[] coerce(GsObject first, GsObject second) {
        GsObject[] objects = new GsObject[2];
        return objects;
    }

    public static boolean areEqual(GsObject first, GsObject second) {
        boolean equal = false;

        if (first.getClass().equals(second.getClass())) {
            if (Util.isNumber(first)) {
                GsNumber firstNumber = (GsNumber)first,
                        secondNumber = (GsNumber)second;

                if (firstNumber.getData() == secondNumber.getData()) {
                    equal = true;
                }
            }
            else if (Util.isString(first)) {
                GsString firstString = (GsString)first,
                        secondString = (GsString)second;

                if (firstString.getData().equals(secondString.getData())) {
                    equal = true;
                }
            }
            else if (Util.isBlock(first)) {
                GsBlock firstBlock = (GsBlock)first,
                        secondBlock = (GsBlock)second;

                if (firstBlock.getData().equals(secondBlock.getData())) {
                    equal = true;
                }
            }
            else if (Util.isArray(first)) {
                List<GsObject> firstData = ((GsArray)first).getData(),
                        secondData = ((GsArray)second).getData();

                int firstSize = firstData.size(),
                        secondSize = secondData.size();

                if (firstSize == secondSize) {
                    equal = true;
                    for (int i = 0; i < firstSize; ++i) {
                        if (!areEqual(firstData.get(i), secondData.get(i))) {
                            equal = false;
                            break;
                        }
                    }
                }
            }
        }

        return equal;
    }
}
