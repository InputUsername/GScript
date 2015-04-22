package com.inputusername.android.gscript.lang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: implement raw/escaped strings
public class Tokenizer {
    // [a-zA-Z_][a-zA-Z_]*|-?[0-9]+|\\{[^\\}]*\\}|'(?:\\.|[^'])*'?|\"(?:\\.|[^\"])*\"?|#[^\n\r]*|.
    private final static String REGEX = "[a-zA-Z_][a-zA-Z_]*|-?[0-9]+|\\{[^\\}]*\\}|'(?:\\.|[^'])*'?|\"(?:\\.|[^\"])*\"?|#[^\n\r]*|.";

    public static ArrayList<Token> tokenize(String code) {
        ArrayList<Token> tokens = new ArrayList<>();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(code);

        while (matcher.find()) {
            Token token = new Token(matcher.group());
            tokens.add(token);
        }

        return tokens;
    }
}
