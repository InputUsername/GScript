package com.inputusername.android.gscript.lang;

import android.util.Log;

import com.inputusername.android.gscript.lang.types.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Interpreter {
    private final static int maxRecursionDepth = 50;

    private static String interpretString(String code, Functions functions, StringBuilder output, Stack stack, Namespace variables) {
        List<Token> tokens = Tokenizer.tokenize(code);
        int tokenCount = tokens.size();

        for (int i = 0; i < tokenCount; ++i) {
            Token token = tokens.get(i);
            String tokenString = token.getTokenString();
            Token.Type tokenType = token.getTokenType();

            // Handle assignment and go to the next token.
            if (tokenString.equals(":")) {
                GsObject value = stack.peek();

                if (i != tokenCount - 1) {
                    Token nextToken = tokens.get(i + 1);
                    String nextTokenString = nextToken.getTokenString();

                    variables.put(nextTokenString, value);
                } else {
                    //TODO: implement exceptions (end of code reached during assignment)
                }

                // Move to next token and then continue the loop, so actually skip the next token.
                ++i;
                continue;
            }

            // If the token (object) was previously assigned a value,
            // retrieve the value and handle it as normal. Then go to the next token.
            if (variables.containsKey(tokenString)) {
                GsObject object = variables.get(tokenString);

                if (Util.isNumber(object) || Util.isString(object) || Util.isArray(object)) {
                    stack.push(object);
                }
                else if (Util.isBlock(object)) {
                    String blockCode = ((GsBlock) object).getData();
                    Log.d("stack before block", stack.toString());
                    Log.d("block code", blockCode);
                    interpretString(blockCode, functions, output, stack, variables);
                    Log.d("stack after block", stack.toString());
                }

                Log.d("stack after block (2)", stack.toString());

                continue;
            }

            if (tokenType == Token.Type.NUMBER) {
                // Parse number and push it
                int numberToken = Integer.parseInt(tokenString);
                GsNumber number = new GsNumber(numberToken);
                stack.push(number);
            }
            else if (tokenType == Token.Type.STRING) {
                // Parse string and push it
                String stringToken = tokenString.substring(1, tokenString.length() - 1);
                GsString string = new GsString(stringToken);
                stack.push(string);
            }
            else if (tokenType == Token.Type.BLOCK) {
                // Parse block and push it
                String blockToken = tokenString.substring(1, tokenString.length() - 1);
                GsBlock block = new GsBlock(blockToken);
                stack.push(block);
            }
            else if (tokenType == Token.Type.COMMENT) {
                // Do nothing! :O
            }
            else if (tokenType == Token.Type.WORD || tokenType == Token.Type.OTHER) {
                if (BuiltIn.exists(tokenString)) {
                    functions.execute(tokenString);
                }
            }
        }

        Iterator<GsObject> iterator = stack.descendingIterator();
        while (iterator.hasNext()) {
            output.append(", ");
            output.append(iterator.next().toString());
        }

        if (output.length() == 0) {
            return "";
        }

        return output.substring(2);
    }

    public static String interpret(String program) {
        Stack stack = new Stack();
        Namespace variables = new Namespace();
        StringBuilder output = new StringBuilder();
        Functions functionsList = new Functions(stack, output);

        String result;

        try {
            result = interpretString(program, functionsList, output, stack, variables);
        }
        catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }
}
