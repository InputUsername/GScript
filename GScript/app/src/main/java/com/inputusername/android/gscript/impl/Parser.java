package com.inputusername.android.gscript.impl;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private final static String REGEX = "/[a-zA-Z_][a-zA-Z0-9_]*|'(?:\\.|[^'])*'?|\"(?:\\.|[^\"])*\"?|-?[0-9]+|#[^\n\r]*|./m";

    public ArrayList<Token> parse(String code) {
        ArrayList<Token> parsedCode = new ArrayList<Token>();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(code);

        while (matcher.find()) {
            String nextToken = matcher.toString();
            String tokenType = Token.tokenType(nextToken);

            Token token = new Token(nextToken, tokenType);

        }

        return parsedCode;
    }
}
