package com.inputusername.android.gscript.lang;

public class Token {

    public static enum Type {
        WORD,
        NUMBER,
        BLOCK,
        STRING,
        COMMENT,
        OTHER
    }

    private String tokenString;
    private Type tokenType;

    public Token(String token, Type tokenType) {
        this.tokenString = token;
        this.tokenType = tokenType;
    }
    public Token(String token) {
        this(token, findTokenType(token));
    }

    private static Type findTokenType(String token) {
        if (token.matches("/[a-zA-Z_][a-zA-Z0-9_]*/")) {
            return Type.WORD;
        }
        else if (token.matches("/-?[0-9]+/")) {
            return Type.NUMBER;
        }
        else if (token.startsWith("{") && token.endsWith("}")) {
            return Type.BLOCK;
        }
        else if (token.matches("/'(?:\\.|[^'])*'?/") || token.matches("/\"(?:\\.|[^\"])*\"?/")) {
            return Type.STRING;
        }
        else if (token.startsWith("#")) {
            return Type.COMMENT;
        }
        else {
            return Type.OTHER;
        }
    }

    public String getTokenString() {
        return tokenString;
    }

    public Type getTokenType() {
        return tokenType;
    }

    public String toString() {
        return tokenType.toString() + " token: " + tokenString;
    }
}
