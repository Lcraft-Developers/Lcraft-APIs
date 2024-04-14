package de.lcraft.lapi.javaUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListArrayUtils {

    public static boolean containsFromStringArray(String[] array, String cont) {
        for(String c : array) { if(c.equalsIgnoreCase(cont)) return true; }
        return false;
    }

    public static String[] makeStringListToArray(List<String> arrayList) {
        String[] array = new String[arrayList.size()];
        for(int i = 0; i < arrayList.size(); i++) array[i] = arrayList.get(i);
        return array;
    }
    public static List<String> makeStringArrayToList(String... array) {
        return new ArrayList<>(Arrays.asList(array));
    }

}
