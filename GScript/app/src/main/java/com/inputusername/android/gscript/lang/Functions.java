package com.inputusername.android.gscript.lang;

import com.inputusername.android.gscript.lang.types.GsArray;
import com.inputusername.android.gscript.lang.types.GsBlock;
import com.inputusername.android.gscript.lang.types.GsNumber;
import com.inputusername.android.gscript.lang.types.GsObject;
import com.inputusername.android.gscript.lang.types.GsString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Functions {

    private Stack stack;
    private StringBuilder output;
    private Namespace variables;

    private int stackSize = 0;

    public Functions(Stack stack, StringBuilder output, Namespace variables) {
        this.stack = stack;
        this.output = output;
        this.variables = variables;
    }

    public void execute(String builtIn) {
        switch (builtIn) {
            case "~":
                bitwiseNot_dump_eval();
                break;
            case "`":
                inspect();
                break;
            case "!":
                logicalNot();
                break;
            case "@":
                rotate();
                break;
            case "$":
                stackIth_sort_sortBy();
                break;
            case "+":
                add_concat();
                break;
            case "-":
                subtract_setDiff();
                break;
            case "*":
                mult_blockExecuteTimes_arrayRepeat_join_fold();
                break;
            case "/":
                div_split_splitInGroupsOfSize_unfold_each();
                break;
            case "%":
                mod_map_everyIthElement_cleanSplit();
                break;
            case "|":
                bitwiseOr_setwiseOr();
                break;
            case "&":
                bitwiseAnd_setwiseAnd();
                break;
            case "^":
                bitwiseXor_setwiseXor();
                break;
            case "[":
                arrayStart();
                break;
            case "]":
                arrayEnd();
                break;
            case "\\":
                swap();
                break;
            case ";":
                pop();
                break;
            case "<":
                lessThan_elementsLessThanIndex();
                break;
            case ">":
                greaterThan_elementsGreaterThanOrEqualToIndex();
                break;
            case "=":
                equalTo_elementAtIndex();
                break;
            case ",":
                range_size_select();
                break;
            case ".":
                dup();
                break;
            case "?":
                pow_index_find();
                break;
            case "(":
                decrement_uncons();
                break;
            case ")":
                increment_rightUncons();
                break;
            case "and":
                and();
                break;
            case "or":
                or();
                break;
            case "xor":
                xor();
                break;
            case "print":
                print();
                break;
            case "p":
                p();
                break;
            case "n":
                n();
                break;
            case "puts":
                puts();
                break;
            case "rand":
                rand();
                break;
            case "do":
                doFunc();
                break;
            case "while":
                whileFunc();
                break;
            case "until":
                until();
                break;
            case "if":
                ifFunc();
                break;
            case "abs":
                abs();
                break;
            case "zip":
                zip();
                break;
            case "base":
                base();
                break;
            default:
                //TODO: implement exceptions (unknown builtin exception)
                break;
        }
    }

    @PartiallyImplemented
    public void bitwiseNot_dump_eval() {
        GsObject object = stack.pop();

        if (Util.isNumber(object)) {
            int number = ((GsNumber)object).getData();
            stack.push(new GsNumber(~number));
        }
        else if (Util.isString(object) || Util.isBlock(object)) {

        }
        else if (Util.isArray(object)) {
            List<GsObject> arrayData = ((GsArray)object).getData();
            int size = arrayData.size();
            for (int i = size - 1; i > 0; --i) {
                stack.push(arrayData.get(i));
            }
        }
    }

    public void inspect() {
        GsObject object = stack.pop();
        stack.push(new GsString(object.toString()));
    }

    public void logicalNot() {
        GsObject object = stack.pop();
        int logicalValue = (Util.truthy(object) ? 0 : 1);
        stack.push(new GsNumber(logicalValue));
    }

    public void rotate() {
        GsObject first = stack.pop(),
                second = stack.pop(),
                third = stack.pop();

        stack.push(third);
        stack.push(first);
        stack.push(second);
    }

    @PartiallyImplemented
    public void stackIth_sort_sortBy() {
        GsObject object = stack.pop();

        if (Util.isNumber(object)) {

        }
        else if (Util.isString(object)) {
            String string = ((GsString)object).getData();
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);
            stack.push(new GsString(sortedString));
        }
        else if (Util.isArray(object)) {
            List<GsObject> arrayData = ((GsArray)object).getData();
            GsObject[] objects = (GsObject[])arrayData.toArray();
            Arrays.sort(objects);
            List<GsObject> newArray = Arrays.asList(objects);
            stack.push(new GsArray(newArray));
        }
        else if (Util.isBlock(object)) {
            //TODO: implement sortBy
        }
    }

    @PartiallyImplemented
    public void add_concat() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.sameClass(first, second)) {
            if (Util.isNumber(first)) {
                int firstNumber = ((GsNumber)first).getData(),
                        secondNumber = ((GsNumber)second).getData();

                int sum = firstNumber + secondNumber;
                stack.push(new GsNumber(sum));
            }
            else if (Util.isString(first)) {
                String firstString = ((GsString)first).getData(),
                        secondString = ((GsString)second).getData();

                String concat = secondString + firstString;
                stack.push(new GsString(concat));
            }
            else if (Util.isBlock(first)) {
                String firstBlock = ((GsBlock)first).getData(),
                        secondBlock = ((GsBlock)second).getData();

                String concat = secondBlock + firstBlock;
                stack.push(new GsBlock(concat));
            }
            else if (Util.isArray(first)) {
                List<GsObject> firstArrayData = ((GsArray)first).getData(),
                        secondArrayData = ((GsArray)second).getData();

                List<GsObject> concat = new ArrayList<>(secondArrayData);
                concat.addAll(firstArrayData);
                stack.push(new GsArray(concat));
            }
        }
        else {
            GsObject[] objects = new GsObject[] {first, second};
            Util.coerce(objects);
            stack.push(objects[1]);
            stack.push(objects[0]);
            //add_concat();
        }
    }

    @PartiallyImplemented
    public void subtract_setDiff() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.sameClass(first, second)) {
            if (Util.isNumber(first)) {
                int firstNumber = ((GsNumber)first).getData(),
                        secondNumber = ((GsNumber)second).getData();

                int difference = secondNumber - firstNumber;
                stack.push(new GsNumber(difference));
            }
        }
        //TODO: finish setDiff
    }

    @PartiallyImplemented
    public void mult_blockExecuteTimes_arrayRepeat_join_fold() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.sameClass(first, second)) {
            if (Util.isNumber(first)) {
                int firstNumber = ((GsNumber)first).getData(),
                        secondNumber = ((GsNumber)second).getData();

                int product = firstNumber * secondNumber;
                stack.push(new GsNumber(product));
            }
        }
        //TODO: finish blockExecuteTimes_arrayRepeat_join_fold
    }

    @Unimplemented
    public void div_split_splitInGroupsOfSize_unfold_each() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.sameClass(first, second)) {
            if (Util.isNumber(first)) {
                int firstNumber = ((GsNumber)first).getData(),
                        secondNumber = ((GsNumber)second).getData();

                if (firstNumber == 0) {
                    throw new ArithmeticException("Divide by zero");
                }

                int quotient = secondNumber / firstNumber;
                stack.push(new GsNumber(quotient));
            }
        }
        //TODO: finish split_splitInGroupsOfSize_unfold_each
    }

    @PartiallyImplemented
    public void mod_map_everyIthElement_cleanSplit() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.isNumber(first) && Util.isNumber(second)) {
            int firstNumber = ((GsNumber)first).getData(),
                    secondNumber = ((GsNumber)second).getData();

            int mod = secondNumber % firstNumber;
            stack.push(new GsNumber(mod));
        }
        else {
            //TODO: finish 'mod map everyIthElement cleanSplit' builtin
        }
    }

    @PartiallyImplemented
    public void bitwiseOr_setwiseOr() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.isNumber(first) && Util.isNumber(second)) {
            int firstNumber = ((GsNumber)first).getData(),
                    secondNumber = ((GsNumber)second).getData();

            int bitwiseOr = firstNumber | secondNumber;
            stack.push(new GsNumber(bitwiseOr));
        }
        else {
            //TODO: finish 'bitwise or/setwise or' builtin
        }
    }

    @PartiallyImplemented
    public void bitwiseAnd_setwiseAnd() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.isNumber(first) && Util.isNumber(second)) {
            int firstNumber = ((GsNumber)first).getData(),
                    secondNumber = ((GsNumber)second).getData();

            int bitwiseAnd = firstNumber & secondNumber;
            stack.push(new GsNumber(bitwiseAnd));
        }
        else {
            //TODO: finish 'bitwise and/setwise and' builtin
        }
    }

    @PartiallyImplemented
    public void bitwiseXor_setwiseXor() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.isNumber(first) && Util.isNumber(second)) {
            int firstNumber = ((GsNumber)first).getData(),
                    secondNumber = ((GsNumber)second).getData();

            int bitwiseXor = firstNumber ^ secondNumber;
            stack.push(new GsNumber(bitwiseXor));
        }
        else {
            //TODO: finish 'bitwise xor/setwise xor' builtin
        }
    }

    public void arrayStart() {
        stackSize = stack.size();
    }

    public void arrayEnd() {
        int arrayCount = stack.size() - stackSize;
        List<GsObject> objects = new ArrayList<>();

        for (int i = 0; i < arrayCount; ++i) {
            objects.add(0, stack.pop());
        }
        GsArray newArray = new GsArray(objects);
        stack.push(newArray);

        stackSize = 0;
    }

    public void swap() {
        GsObject first = stack.pop(), second = stack.pop();
        stack.push(first);
        stack.push(second);
    }

    public void pop() {
        stack.pop();
    }

    @PartiallyImplemented
    public void lessThan_elementsLessThanIndex() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.sameClass(first, second)) {
            if (Util.isSmaller(second, first)) {
                stack.push(new GsNumber(1));
            }
            else {
                stack.push(new GsNumber(0));
            }
        }
        else {
            if (Util.isNumber(first) && !Util.isNumber(second)) {

            }
            else {

            }
        }
    }

    @PartiallyImplemented
    public void greaterThan_elementsGreaterThanOrEqualToIndex() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.sameClass(first, second)) {
            if (Util.isLarger(second, first)) {
                stack.push(new GsNumber(1));
            }
            else {
                stack.push(new GsNumber(0));
            }
        }
        else {
            //TODO: elements greater than or equal index
        }
    }

    @PartiallyImplemented
    public void equalTo_elementAtIndex() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.sameClass(first, second)) {
            if (Util.areEqual(first, second)) {
                stack.push(new GsNumber(1));
            }
            else {
                stack.push(new GsNumber(0));
            }
        }
        else {
            //TODO: array/string/block index
        }
    }

    @PartiallyImplemented
    public void range_size_select() {
        GsObject object = stack.pop();

        if (Util.isNumber(object)) {
            int number = ((GsNumber)object).getData();
            List<GsObject> newArrayData = new ArrayList<>();

            for (int i = 0; i < number; i++) {
                newArrayData.add(new GsNumber(i));
            }

            stack.push(new GsArray(newArrayData));
        }
        else if (Util.isArray(object)) {
            List<GsObject> arrayData = ((GsArray)object).getData();
            int size = arrayData.size();
            stack.push(new GsNumber(size));
        }
    }

    @PartiallyImplemented
    public void dup() {
        //TODO: deep copy of arrays
        stack.push(stack.peek());
    }

    @PartiallyImplemented
    public void pow_index_find() {
        GsObject first = stack.pop(),
                second = stack.pop();

        if (Util.isNumber(first) && Util.isNumber(second)) {
            int firstNumber = ((GsNumber)first).getData(),
                    secondNumber = ((GsNumber)second).getData();

            int power = (int)Math.pow((double)secondNumber, (double)firstNumber);
            stack.push(new GsNumber(power));
        }
        else if (Util.isArray(first) || Util.isArray(second)) {
            GsObject needle = null;
            List<GsObject> haystack = null;

            if (Util.isArray(first)) {
                needle = second;
                haystack = ((GsArray)first).getData();
            }
            else if (Util.isArray(second)) {
                needle = first;
                haystack = ((GsArray)second).getData();
            }

            if (needle == null || haystack == null) {
                return;
            }

            int index = haystack.indexOf(needle);
            stack.push(new GsNumber(index));
        }
        else {
            //TODO: find
        }
    }

    public void decrement_uncons() {
        GsObject object = stack.pop();

        if (Util.isNumber(object)) {
            int number = ((GsNumber)object).getData();
            stack.push(new GsNumber(number - 1));
        }
        else if (Util.isArray(object)) {
            GsArray array = (GsArray)object;
            List<GsObject> arrayData = array.getData();
            GsObject leftObject = arrayData.get(0);
            GsArray newArray = new GsArray(arrayData.subList(1, arrayData.size()));
            stack.push(newArray);
            stack.push(leftObject);
        }
    }

    public void increment_rightUncons() {
        GsObject object = stack.pop();

        if (Util.isNumber(object)) {
            int number = ((GsNumber)object).getData();
            stack.push(new GsNumber(number + 1));
        }
        else if (Util.isArray(object)) {
            GsArray array = (GsArray)object;
            List<GsObject> arrayData = array.getData();
            GsObject leftObject = arrayData.get(arrayData.size() - 1);
            GsArray newArray = new GsArray(arrayData.subList(0, arrayData.size() - 1));
            stack.push(newArray);
            stack.push(leftObject);
        }
    }

    @Unimplemented
    public void and() {

    }

    @Unimplemented
    public void or() {

    }

    @Unimplemented
    public void xor() {

    }

    public void print() {
        GsObject object = stack.pop();
        if (Util.isString(object)) {
            output.append(((GsString)object).getData());
        }
        else {
            GsString string = new GsString(object.toString());
            output.append(string);
        }
    }

    public void p() {
        inspect();
        puts();
    }

    public void n() {
        stack.push(new GsString("\n"));
    }

    public void puts() {
        print();
        n();
        print();
    }

    public void rand() {
        GsObject object = stack.pop();

        if (Util.isNumber(object)) {
            int number = ((GsNumber)object).getData();
            int random = (int)(Math.random() * number);
            stack.push(new GsNumber(random));
        }
    }

    public void doFunc() {
        GsObject object = stack.pop();

        if (Util.isBlock(object)) {
            String blockCode = ((GsBlock)object).getData();
            Functions functions = new Functions(stack, output, variables);
            Interpreter.interpretString(blockCode, functions, output, stack, variables);
            while (true) {
                GsObject condition = stack.pop();
                if (!Util.truthy(condition)) {
                    break;
                }
                Interpreter.interpretString(blockCode, functions, output, stack, variables);
            }
        }
    }

    public void whileFunc() {
        GsObject condition = stack.pop();

        if (Util.isBlock(condition)) {
            GsObject body = stack.pop();

            if (Util.isBlock(body)) {
                String conditionCode = ((GsBlock)condition).getData(),
                        bodyCode = ((GsBlock)body).getData();

                Functions functions = new Functions(stack, output, variables);
                while (true) {
                    Interpreter.interpretString(conditionCode, functions, output, stack, variables);
                    GsObject conditionValue = stack.pop();
                    if (!Util.truthy(conditionValue)) {
                        break;
                    }
                    Interpreter.interpretString(bodyCode, functions, output, stack, variables);
                }
            }
        }
    }

    public void until() {
        GsObject condition = stack.pop();

        if (Util.isBlock(condition)) {
            GsObject body = stack.pop();

            if (Util.isBlock(body)) {
                String conditionCode = ((GsBlock)condition).getData(),
                        bodyCode = ((GsBlock)body).getData();

                Functions functions = new Functions(stack, output, variables);
                while (true) {
                    Interpreter.interpretString(conditionCode, functions, output, stack, variables);
                    GsObject conditionValue = stack.pop();
                    if (Util.truthy(conditionValue)) {
                        break;
                    }
                    Interpreter.interpretString(bodyCode, functions, output, stack, variables);
                }
            }
        }
    }

    @PartiallyImplemented
    public void ifFunc() {
        GsObject ifFalse = stack.pop(),
                ifTrue = stack.pop(),
                condition = stack.pop();

        // The value is selected based on the truth value of the condition
        GsObject clause = (Util.truthy(condition) ? ifTrue : ifFalse);

        if (Util.isBlock(clause)) {
            String blockCode = ((GsBlock)clause).getData();
            Functions functions = new Functions(stack, output, variables);
            Interpreter.interpretString(blockCode, functions, output, stack, variables);
        }
        else {
            stack.push(clause);
        }
    }

    public void abs() {
        GsObject object = stack.pop();

        if (Util.isNumber(object)) {
            int number = Math.abs(((GsNumber)object).getData());
            stack.push(new GsNumber(number));
        }
    }

    @Unimplemented
    public void zip() {

    }

    @Unimplemented
    public void base() {

    }

}
