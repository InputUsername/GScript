package com.inputusername.android.gscript.lang;

import com.inputusername.android.gscript.lang.types.GsArray;
import com.inputusername.android.gscript.lang.types.GsBlock;
import com.inputusername.android.gscript.lang.types.GsNumber;
import com.inputusername.android.gscript.lang.types.GsObject;
import com.inputusername.android.gscript.lang.types.GsString;

import java.util.List;

public class Util {
    private Util() {}

    public static String escape(String string) {
        return string;
    }

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

    public static boolean sameClass(GsObject first, GsObject second) {
        return first.getClass().equals(second.getClass());
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

    private static int typeRank(GsObject object) {
        if (isNumber(object)) {
            return 0;
        }
        else if (isArray(object)) {
            return 1;
        }
        else if (isString(object)) {
            return 2;
        }
        else if (isBlock(object)) {
            return 3;
        }
        return 0;
    }
    public static void coerce(GsObject[] objects) {
        int highestRank = 0;

        int length = objects.length;
        for (int i = 0; i < length; ++i) {
            int rank = typeRank(objects[i]);
            if (rank > highestRank) {
                highestRank = rank;
            }
        }
        for (int i = 0; i < length; ++i) {
            int rank = typeRank(objects[i]);
            if (rank < highestRank) {
                //TODO: finish coerce
            }
        }
    }

    /*
        first > second --> 1
        first == second --> 0
        first < second --> -1
     */
    private static Integer compare(GsObject first, GsObject second) {
        Integer equality = null;

        if (sameClass(first, second)) {
            if (Util.isNumber(first)) {
                int firstNumber = ((GsNumber)first).getData(),
                        secondNumber = ((GsNumber)second).getData();

                if (firstNumber < secondNumber) {
                    equality = -1;
                }
                else if (secondNumber == firstNumber) {
                    equality = 0;
                }
                else {
                    equality = 1;
                }
            }
            else if (Util.isString(first)) {
                String firstString = ((GsString)first).getData(),
                        secondString = ((GsString)second).getData();

                equality = firstString.compareTo(secondString);
            }
            else if (Util.isBlock(first)) {
                String firstBlock = ((GsBlock)first).getData(),
                        secondBlock = ((GsBlock)second).getData();

                equality = firstBlock.compareTo(secondBlock);
            }
            else if (Util.isArray(first)) {
                List<GsObject> firstData = ((GsArray)first).getData(),
                        secondData = ((GsArray)second).getData();

                int firstSize = firstData.size(),
                        secondSize = secondData.size();

                if (firstSize == secondSize) {
                    equality = 0;
                    for (int i = 0; i < firstSize; ++i) {
                        Integer comparison = compare(firstData.get(i), secondData.get(i));
                        if (comparison == null) {
                            equality = null;
                            break;
                        }
                        else if (comparison.compareTo(0) < 0) {
                            equality = -1;
                            break;
                        }
                        else if (comparison.compareTo(0) > 0) {
                            equality = 1;
                            break;
                        }
                    }
                }
            }
        }

        return equality;
    }

    /*
    public static boolean areEqual(GsObject first, GsObject second) {
        boolean equal = false;

        if (sameClass(first, second) {
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
    */

    public static boolean areEqual(GsObject first, GsObject second) {
        Integer comparison = compare(first, second);
        if (comparison != null && comparison.equals(0)) {
            return true;
        }
        return false;
    }

    // is first larger than second?
    public static boolean isLarger(GsObject first, GsObject second) {
        Integer comparison = compare(first, second);
        if (comparison != null && comparison.equals(1)) {
            return true;
        }
        return false;
    }

    // is first smaller than second?
    public static boolean isSmaller(GsObject first, GsObject second) {
        return isLarger(second, first);
    }
}
