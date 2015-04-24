package com.inputusername.android.gscript.lang;

import com.inputusername.android.gscript.lang.types.GsArray;
import com.inputusername.android.gscript.lang.types.GsBlock;
import com.inputusername.android.gscript.lang.types.GsNumber;
import com.inputusername.android.gscript.lang.types.GsObject;
import com.inputusername.android.gscript.lang.types.GsString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eigenaar on 23-4-2015.
 */
public class Functions {

    private Stack stack;
    private StringBuilder output;

    private int stackSize = -1;

    public Functions(Stack stack, StringBuilder output) {
        this.stack = stack;
        this.output = output;
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
                stackIth_sortBy();
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

    private void bitwiseNot_dump_eval() {

    }

    private void inspect() {

    }

    private void logicalNot() {
        GsObject object = stack.pop();
        int logicalValue = (Util.truthy(object) ? 0 : 1);
        stack.push(new GsNumber(logicalValue));
    }

    private void rotate() {
        GsObject first = stack.pop(),
                second = stack.pop(),
                third = stack.pop();

        stack.push(third);
        stack.push(first);
        stack.push(second);
    }

    private void stackIth_sortBy() {

    }

    private void add_concat() {

    }

    private void subtract_setDiff() {

    }

    private void mult_blockExecuteTimes_arrayRepeat_join_fold() {

    }

    private void div_split_splitInGroupsOfSize_unfold_each() {

    }

    private void mod_map_everyIthElement_cleanSplit() {

    }

    private void bitwiseOr_setwiseOr() {

    }

    private void bitwiseAnd_setwiseAnd() {

    }

    private void bitwiseXor_setwiseXor() {

    }

    private void arrayStart() {
        stackSize = stack.size();
    }

    private void arrayEnd() {
        if (stackSize != -1) {
            int arrayCount = stack.size() - stackSize;
            List<GsObject> objects = new ArrayList<>();

            for (int i = 0; i < arrayCount; ++i) {
                objects.add(stack.pop());
            }
            GsArray newArray = new GsArray(objects);
            stack.push(newArray);

            stackSize = -1;
        }
        else {
            //TODO: implement exceptions (stackSize not set)
        }
    }

    private void swap() {
        GsObject first = stack.pop(), second = stack.pop();
        stack.push(first);
        stack.push(second);
    }

    private void pop() {
        stack.pop();
    }

    private void lessThan_elementsLessThanIndex() {

    }

    private void greaterThan_elementsGreaterThanOrEqualToIndex() {

    }

    private void equalTo_elementAtIndex() {

    }

    private void range_size_select() {
        GsObject object = stack.pop();

        if (object instanceof GsNumber) {
            int number = ((GsNumber)object).getData();
            List<GsObject> newArrayData = new ArrayList<>();

            for (int i = 0; i < number; i++) {
                newArrayData.add(new GsNumber(i));
            }

            stack.push(new GsArray(newArrayData));
        }
    }

    private void dup() {
        stack.push(stack.peek());
    }

    private void pow_index_find() {

    }

    private void decrement_uncons() {
        GsObject object = stack.pop();

        if (object instanceof GsNumber) {
            int number = ((GsNumber)object).getData();
            stack.push(new GsNumber(number - 1));
        }
        else if (object instanceof GsArray) {
            GsArray array = (GsArray)object;
            List<GsObject> arrayData = array.getData();
            GsObject leftObject = arrayData.get(0);
            GsArray newArray = new GsArray(arrayData.subList(1, arrayData.size()));
            stack.push(newArray);
            stack.push(leftObject);
        }
    }

    private void increment_rightUncons() {
        GsObject object = stack.pop();

        if (object instanceof GsNumber) {
            int number = ((GsNumber)object).getData();
            stack.push(new GsNumber(number + 1));
        }
        else if (object instanceof GsArray) {
            GsArray array = (GsArray)object;
            List<GsObject> arrayData = array.getData();
            GsObject leftObject = arrayData.get(arrayData.size() - 1);
            GsArray newArray = new GsArray(arrayData.subList(0, arrayData.size() - 2));
            stack.push(newArray);
            stack.push(leftObject);
        }
    }

    private void and() {

    }

    private void or() {

    }

    private void xor() {

    }

    private void print() {
        GsObject object = stack.pop();
        if (object instanceof GsString) {
            stack.push(object);
        }
        else {
            GsString string = new GsString(object.toString());
            stack.push(string);
        }
    }

    private void p() {
        inspect();
        puts();
    }

    private void n() {
        stack.push(new GsString("\n"));
    }

    private void puts() {
        print();
        n();
        print();
    }

    private void rand() {
        GsObject object = stack.pop();

        if (object instanceof GsNumber) {
            int number = ((GsNumber)object).getData();
            int random = (int)(Math.random() * number);
            stack.push(new GsNumber(random));
        }
    }

    private void doFunc() {
        GsObject object = stack.pop();

        if (object instanceof GsBlock) {

        }
    }

    private void whileFunc() {

    }

    private void until() {

    }

    private void ifFunc() {
        GsObject ifFalse = stack.pop(),
                ifTrue = stack.pop(),
                condition = stack.pop();

        if (Util.truthy(condition)) {
            if (ifTrue instanceof GsBlock) {
                //TODO: finish 'if' builtin
            }
            else {
                stack.push(ifTrue);
            }
        }
        else {

        }
    }

    private void abs() {
        GsObject object = stack.pop();

        if (object instanceof GsNumber) {
            int number = Math.abs(((GsNumber)object).getData());
            stack.push(new GsNumber(number));
        }
        else {
            //TODO: implement exceptions (incorrect data type for abs)
        }
    }

    private void zip() {

    }

    private void base() {

    }

}
