package com.inputusername.android.gscript.lang;

import java.util.Arrays;
import java.util.List;

public class BuiltIn {
    private final static List<String> builtIns = Arrays.asList(
            "~",
            "`",
            "!",
            "@",
            "$",
            "+",
            "-",
            "*",
            "/",
            "%",
            "|",
            "&",
            "^",
            "[",
            "]",
            "\\",
            ";",
            "<",
            ">",
            "=",
            ",",
            ".",
            "?",
            "(",
            ")",
            "and",
            "or",
            "xor",
            "print",
            "p",
            "n",
            "puts",
            "rand",
            "do",
            "while",
            "until",
            "if",
            "abs",
            "zip",
            "base"
    );

    public static boolean exists(String builtIn) {
        return builtIns.contains(builtIn);
    }
}
