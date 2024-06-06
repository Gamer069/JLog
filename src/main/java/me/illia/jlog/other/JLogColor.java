package me.illia.jlog.other;

import java.util.ArrayList;
import java.util.Collections;

public final class JLogColor {

    public static final JLogColor INSTANCE = new JLogColor();

    // CREDITS:
    // JLogColor code strings from:
    // http://www.topmudsites.com/forums/mud-coding/413-java-ansi.html

    private static final String LG = "\u001B[38;5;78m";
    public static final JLogColor LIGHT_GREEN = new JLogColor(LG);
    private static final String SN = "\u001B[0m";
    private static final String DR = "\u001B[38;5;124m";
    public static final JLogColor DARK_RED = new JLogColor(DR);
    private static final String HI = "\u001B[1m";
    public static final JLogColor HIGH_INTENSITY = new JLogColor(HI);
    public static final JLogColor BOLD = HIGH_INTENSITY;
    private static final String LI = "\u001B[2m";
    public static final String RST = "\033[0m";
    public static final JLogColor RESET = new JLogColor(RST);
    public static final JLogColor LOW_INTENSITY = new JLogColor(LI);
    public static final JLogColor NORMAL = LOW_INTENSITY;
    private static final String I = "\u001B[3m";
    public static final JLogColor ITALIC = new JLogColor(I);
    private static final String U = "\u001B[4m";
    public static final JLogColor UNDERLINE = new JLogColor(U);
    private static final String B = "\u001B[5m";
    public static final JLogColor BLINK = new JLogColor(B);
    public static final JLogColor BLUE = new JLogColor(B);
    private static final String RB = "\u001B[6m";
    public static final JLogColor RAPID_BLINK = new JLogColor(RB);
    private static final String RV = "\u001B[7m";
    private static final String IT = "\u001B[8m";
    private static final String BL = "\u001B[30m";
    public static final JLogColor BLACK = new JLogColor(BL);
    private static final String R = "\u001B[31m";
    public static final JLogColor RED = new JLogColor(R);
    private static final String G = "\u001B[32m";
    public static final JLogColor GREEN = new JLogColor(G);
    private static final String Y = "\u001B[33m";
    public static final JLogColor YELLOW = new JLogColor(Y);
    private static final String Bl = "\u001B[34m";
    private static final String M = "\u001B[35m";
    public static final JLogColor MAGENTA = new JLogColor(M);
    private static final String C = "\u001B[36m";
    public static final JLogColor CYAN = new JLogColor(C);
    private static final String W = "\u001B[37m";
    public static final JLogColor WHITE = new JLogColor(W);
    private static final String BG_BL = "\u001B[40m";
    public static final JLogColor BG_BLACK = new JLogColor(BG_BL);
    private static final String BG_R = "\u001B[41m";
    public static final JLogColor BG_RED = new JLogColor(BG_R);
    private static final String BG_G = "\u001B[42m";
    public static final JLogColor BG_GREEN = new JLogColor(BG_G);
    private static final String BG_Y = "\u001B[43m";
    public static final JLogColor BG_YELLOW = new JLogColor(BG_Y);
    private static final String BG_B = "\u001B[44m";
    public static final JLogColor BG_BLUE = new JLogColor(BG_B);
    private static final String BG_M = "\u001B[45m";
    public static final JLogColor BG_MAGENTA = new JLogColor(BG_M);
    private static final String BG_CYA = "\u001B[46m";
    public static final JLogColor BG_CYAN = new JLogColor(BG_CYA);
    private static final String BG_W = "\u001B[47m";
    public static final JLogColor BG_WHITE = new JLogColor(BG_W);

    private final String[] codes;
    private final String codes_str;
    private String code;

    public JLogColor(String... codes) {
        this.codes = codes;
        StringBuilder _codes_str = new StringBuilder();
        for (String code : codes) {
            _codes_str.append(code);
        }
        codes_str = _codes_str.toString();
    }

    public static JLogColor of(String code) {
        INSTANCE.code = code;

        return new JLogColor(code);
    }

    public JLogColor and(JLogColor other) {
        ArrayList<String> both = new ArrayList<>();
        Collections.addAll(both, codes);
        Collections.addAll(both, other.codes);
        return new JLogColor(both.toArray(new String[]{}));
    }

    public String colorize(String original) {
        return codes_str + original + SN;
    }

    public String format(String template, Object... args) {
        return colorize(String.format(template, args));
    }

    public String normalize() {
        INSTANCE.code = codes_str;
        return codes_str;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return normalize();
    }
}