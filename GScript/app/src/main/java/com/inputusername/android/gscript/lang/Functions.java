package com.inputusername.android.gscript.lang;

import com.inputusername.android.gscript.lang.types.GsArray;
import com.inputusername.android.gscript.lang.types.GsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eigenaar on 23-4-2015.
 */
public class Functions {

    private Stack programStack;
    private StringBuilder programOutput;

    private int stackSize = -1;

    public Functions(Stack stack, StringBuilder output) {
        programStack = stack;
        programOutput = output;
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

    }

    private void rotate() {
        GsObject first = programStack.pop(),
                second = programStack.pop(),
                third = programStack.pop();

        programStack.push(third);
        programStack.push(first);
        programStack.push(second);
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
        stackSize = programStack.size();
    }

    private void arrayEnd() {
        if (stackSize != -1) {
            int arrayCount = programStack.size() - stackSize;
            List<GsObject> objects = new ArrayList<>();

            for (int i = arrayCount; i > 0; --i) {
                objects.add(programStack.pop());
            }
            GsArray newArray = new GsArray(objects);
            programStack.push(newArray);

            stackSize = -1;
        }
        else {
            //TODO: implement exceptions (stackSize not set)
        }
    }

    private void swap() {
        GsObject first = programStack.pop(), second = programStack.pop();
        programStack.push(first);
        programStack.push(second);
    }

    private void pop() {
        programStack.pop();
    }

    private void lessThan_elementsLessThanIndex() {

    }

    private void greaterThan_elementsGreaterThanOrEqualToIndex() {

    }

    private void equalTo_elementAtIndex() {

    }

    private void range_size_select() {

    }

    private void dup() {
        programStack.push(programStack.peek());
    }

    private void pow_index_find() {

    }

    private void decrement_uncons() {

    }

    private void increment_rightUncons() {

    }

    private void and() {

    }

    private void or() {

    }

    private void xor() {

    }

    private void print() {

    }

    private void p() {

    }

    private void n() {

    }

    private void puts() {

    }

    private void rand() {

    }

    private void doFunc() {

    }

    private void whileFunc() {

    }

    private void until() {

    }

    private void ifFunc() {

    }

    private void abs() {

    }

    private void zip() {

    }

    private void base() {

    }

}
