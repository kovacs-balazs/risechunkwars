package me.koba1.risechunkwars.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static void main(String[] args) {
        int hash = hashCode(1,5);
        System.out.println(hash);
        System.out.println("----");
        for (int i : fromHashCode(hash)) {
            System.out.println(i);
        }
    }

    public static int hashCode(int i, int j) {
        return (i + j) * (i + j + 1) / 2 + j;
    }
    public static int[] fromHashCode(int n) {
        int w = (int) Math.floor ((Math.sqrt(8 * n + 1) - 1) / 2);
        int t = w * (w + 1) / 2;
        int j = n - t;
        int i = w - j;
        return new int[]{i,j};
    }
}
