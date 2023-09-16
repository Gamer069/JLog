package me.illia.jlog;

import org.jetbrains.annotations.NotNull;

public class JParser {
    public static @NotNull String parse(String input, Object... args) {
        if (input == null) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        int argIndex = 0;
        int startPos = 0;

        while (true) {
            int openBrace = input.indexOf("{}", startPos);
            if (openBrace == -1) {
                break; // No more {}'s found
            }

            result.append(input, startPos, openBrace); // Append text before {}
            try {
                if (argIndex < args.length) {
                    result.append(args[argIndex]); // Append argument
                    argIndex++;
                } else {
                    result.append("{}"); // No more arguments, append {} as is
                }
            } catch (NullPointerException e) {
                result.append("null");
            }

            startPos = openBrace + 2; // Move startPos past {}
        }

        result.append(input, startPos, input.length()); // Append any remaining text

        return result.toString().replace("{/}", "{}");
    }
}
