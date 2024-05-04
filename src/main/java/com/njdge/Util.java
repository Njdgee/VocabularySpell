package com.njdge;

public class Util {
    public static String getHint(String word) {
        // 取得字首和字尾
        if (word.length() < 3) {
            return "_";
        }
        char firstChar = word.charAt(0);
        char lastChar = word.charAt(word.length() - 1);

        // 將字中間的字母替換為 _
        StringBuilder hint = new StringBuilder();
        hint.append(firstChar);
        for (int i = 1; i < word.length() - 1; i++) {
            hint.append("_");
        }
        hint.append(lastChar);
        return hint.toString();
    }
}
