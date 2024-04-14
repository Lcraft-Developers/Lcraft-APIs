package de.lcraft.lapi.configurationSystem.yaml.managment;

import de.lcraft.lapi.configurationSystem.api.yaml.loadSystem.LoadItem;
import de.lcraft.lapi.configurationSystem.api.yaml.managment.Item;
import de.lcraft.lapi.javaUtils.ListArrayUtils;
import de.lcraft.lapi.javaUtils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ConverterCalculator implements de.lcraft.lapi.configurationSystem.api.yaml.managment.ConverterCalculator {

    private static final String separator = ".";
    private static final String splitSeparator = "\\.";
    private static final String keyValueSeparator = ": ";
    private static final String commentIndicator = "#";

    private static final String spaceSymbol = " ";
    private static final Integer spaceAmountPerLine = 4;

    @Override
    public Item convertRawLineToItem(String rawLine, String[] beforeLines) {
        return convertRawLineToLoadItem(rawLine, beforeLines);
    }
    @Override
    public LoadItem convertRawLineToLoadItem(String rawLine, String[] beforeLines) {
        String root;
        String id;
        String completeRoot;
        Integer weight;
        String rawValue;
        Object value;
        String line;

        line = convertRawLineToLine(rawLine);
        root = genRootFromRawLine(rawLine, beforeLines);
        id = getIDFromLine(line);
        completeRoot = expandRootToCompleteRootWithID(root, id);
        weight = calcWeightByRawLine(rawLine);
        rawValue = getRawValue(line);
        value = getValue(line);

        return new de.lcraft.lapi.configurationSystem.yaml.loadSystem.LoadItem(root, id, completeRoot, weight, rawValue, value, rawLine, line);
    }
    @Override
    public Boolean isLineComment(String rawLine) {
        return convertRawLineToLine(rawLine).startsWith(getCommentIndicator());
    }

    @Override
    public String genRootFromRawLine(String rawLine, String[] beforeLines) {
        String root = "";
        int weight = calcWeightByRawLine(rawLine);

        if(weight == 0) return root;
        for(int i = weight; i > 0; i-- ) {
            String nearestID = getIDFromLine(convertRawLineToLine(findNearestParentLine(beforeLines, i)));
            if(!root.isEmpty()) root = nearestID + getSeparator() + root;
            else root = nearestID;
        }
        return root;
    }
    @Override
    public String genCompleteRootFromRawLine(String rawLine, String[] beforeLines) {
        String root = genRootFromRawLine(rawLine, beforeLines);
        if(root.isEmpty()) return getIDFromLine(convertRawLineToLine(rawLine));
        return genRootFromRawLine(rawLine, beforeLines) + getSeparator() + getIDFromLine(convertRawLineToLine(rawLine));
    }
    @Override
    public String findNearestParentLine(String[] beforeLines, int weightFromLastLine) {
        for(int i = beforeLines.length - 1; i >= 0; i--) {
            String rawCurrentLine = beforeLines[i];
            if(calcWeightByRawLine(rawCurrentLine) < weightFromLastLine) {
                return rawCurrentLine;
            }
        }
        return "";
    }

    @Override
    public Integer calcWeightByRawLine(String rawLine) {
        int weight = 0;
        while(rawLine.startsWith(" ")) {
            rawLine = rawLine.replaceFirst(" ", "");
            weight++;
        }
        return weight / getSpaceAmountPerLine();
    }
    @Override
    public Integer calcWeightByRoot(String root) {
        return calcWeightByCompleteRoot(root) + 1;
    }
    @Override
    public Integer calcWeightByCompleteRoot(String completeRoot) {
        int length = completeRoot.split(getSplitSeparator()).length;
        if(length == 0) return 0;
        return length - 1;
    }

    @Override
    public String convertWeightToSpace(Integer weight) {
        return getSpaceSymbol().repeat(Math.max(0, weight * getSpaceAmountPerLine()));
    }

    @Override
    public String convertRawLineToLine(String rawLine) {
        while(rawLine.startsWith(getSpaceSymbol())) rawLine = rawLine.replaceFirst(getSpaceSymbol(), "");
        return rawLine;
    }
    @Override
    public String convertLineToRawLine(String line, Integer weight) {
        return convertWeightToSpace(weight) + line;
    }

    @Override
    public String genNewLine(Item item) {
        return genNewLine(item.getWeight(), item.getID(), item.getRawValue(), item.getComments());
    }
    @Override
    public String genNewLine(Integer weight, String id, Object value, List<String> comments) {
        String newLine = convertWeightToSpace(weight) + id + getKeyValueSeparator() + value;
        StringBuilder commentsLines = new StringBuilder();
        for(String c : comments) commentsLines.append(convertWeightToSpace(weight)).append(getCommentIndicator()).append(" ").append(c).append(System.lineSeparator());
        return commentsLines + newLine;
    }
    @Override
    public String genNewLine(String root, String id, Object value, List<String> comments) {
        return genNewLine(calcWeightByRoot(root), id, value, comments);
    }

    @Override
    public Item createItem(String root, String id, String completeRoot, Integer weight, String rawValue, Object value) {
        return new de.lcraft.lapi.configurationSystem.yaml.managment.Item(root, id, completeRoot, weight, rawValue, value);
    }
    @Override
    public Item createItem(String completeRoot, String rawValue) {
        return createItem(cutCompleteRootToRoot(completeRoot), getIDFromCompleteRoot(completeRoot), completeRoot, calcWeightByCompleteRoot(completeRoot), rawValue, rawValue);
    }
    @Override
    public Item createItem(String completeRoot, Object value) {
        return createItem(completeRoot, value.toString());
    }

    @Override
    public String[] splitLine(String line) {
        return line.split(getKeyValueSeparator());
    }
    @Override
    public String getIDFromLine(String line) {
        String[] split = splitLine(line);
        if(split.length > 0) return splitLine(line)[0];
        return "";
    }
    @Override
    public String getIDFromCompleteRoot(String completeRoot) {
        List<String> list = ListArrayUtils.makeStringArrayToList(completeRoot.split(getSplitSeparator()));
        if(!list.isEmpty()) return list.getLast();
        return "";
    }
    @Override
    public String getRawValue(String line) {
        String[] split = splitLine(line);
        if(split.length > 1) return splitLine(line)[1];
        return "";
    }
    @Override
    public Object getValue(String line) {
        return getRawValue(line);
    }

    @Override
    public String expandRootToCompleteRootWithLine(String root, String line) {
        return expandRootToCompleteRootWithID(root, getIDFromLine(line));
    }
    @Override
    public String expandRootToCompleteRootWithID(String root, String id) {
        if(root.isEmpty()) return id;
        return root + getSeparator() + id;
    }
    @Override
    public String cutCompleteRootToRoot(String completeRoot) {
        return StringUtils.replaceLast(completeRoot, getSeparator() + getIDFromCompleteRoot(completeRoot), "");
    }

    @Override
    public Item simplifyLoadItem(LoadItem loadItem) {
        return loadItem;
    }
    @Override
    public LoadItem extendItem(Item item, String rawLine, String line) {
        return new de.lcraft.lapi.configurationSystem.yaml.loadSystem.LoadItem(item.getRoot(), item.getID(), item.getCompleteRoot(), item.getWeight(), item.getRawValue(), item.getValue(), rawLine, line);
    }
    @Override
    public List<Item> simplifyLoadItemList(List<LoadItem> loadItems) {
        List<Item> items = new ArrayList<>();
        loadItems.forEach(loadItem -> items.add(simplifyLoadItem(loadItem)));
        return items;
    }

    public static String getCommentIndicator() {
        return commentIndicator;
    }
    public static String getSeparator() {
        return separator;
    }
    public static String getSplitSeparator() {
        return splitSeparator;
    }
    public static String getSpaceSymbol() {
        return spaceSymbol;
    }
    public static Integer getSpaceAmountPerLine() {
        return spaceAmountPerLine;
    }
    public static String getKeyValueSeparator() {
        return keyValueSeparator;
    }

    /*
    @Override
    public int calcWeightByRawLine(String rawLine) {
        return rawLine.split(space).length / spaces_amount;
    }
    @Override
    public String genWhiteSpaces(Integer weight) {
        return space.repeat(Math.max(0, weight * spaces_amount));
    }
    @Override
    public String removeWhiteSpaces(String rawLine) {
        String c = rawLine;
        while(c.startsWith(space)) c = c.replace(space, "");
        return c;
    }
    @Override
    public String[] splitLineInTwo(String line) {
        return line.split(key_value_separator);
    }

    @Override
    public String genRootFromRawLine(String rawLine, String[] beforeLines) {
        StringBuilder root = new StringBuilder(splitLineInTwo(removeWhiteSpaces(rawLine))[0]);
        int weight = calcWeightByRawLine(rawLine);

        if(weight == 0) return root.toString();
        for(int i = weight; i > 0; i--) {
            String nearestRawLine = getNearestParentLine(beforeLines, i);
            String nearestLine = removeWhiteSpaces(nearestRawLine);
            String nearestKey = splitLineInTwo(nearestLine)[0];
            root.insert(0, nearestKey + ".");
        }
        return root.toString();
    }
    private String getNearestParentLine(String[] beforeLines, int weightFromLastLine) {
        for(int i = beforeLines.length - 1; i >= 0; i--) {
            String rawCurrentLine = beforeLines[i];
            if(calcWeightByRawLine(rawCurrentLine) >= weightFromLastLine) continue;
            return rawCurrentLine;
        }
        return "";
    }

    @Override
    public int calcWeightByRoot(String root) {
        return StringUtils.lengthAllCaseLetters(".");
    }
    @Override
    public String getNewLine(String root, String key, Object rawValue) {
        return genWhiteSpaces(calcWeightByRoot(root)) + key + key_value_separator + rawValue;
    }

    @Override
    public Item simplifyLoadItem(LoadItem loadItem) {
        return loadItem;
    }
    @Override
    public List<Item> simplifyLoadItemList(List<LoadItem> loadItems) {
        List<Item> newList = new ArrayList<>();
        loadItems.forEach(loadItem -> newList.add(simplifyLoadItem(loadItem)));
        return newList;
    }
    */

}
