package de.lcraft.lapi.configurationSystem.api.yaml.managment;

import de.lcraft.lapi.configurationSystem.api.yaml.loadSystem.LoadItem;
import java.util.List;

public interface ConverterCalculator {

    Item convertRawLineToItem(String rawLine, String[] beforeLines);
    LoadItem convertRawLineToLoadItem(String rawLine, String[] beforeLines);
    Boolean isLineComment(String rawLine);

    String genRootFromRawLine(String rawLine, String[] beforeLines);
    String genCompleteRootFromRawLine(String rawLine, String[] beforeLines);
    String findNearestParentLine(String[] beforeLines, int weightFromLastLine);

    Integer calcWeightByRawLine(String rawLine);
    Integer calcWeightByRoot(String root);
    Integer calcWeightByCompleteRoot(String completeRoot);

    String convertWeightToSpace(Integer weight);

    String convertRawLineToLine(String rawLine);
    String convertLineToRawLine(String line, Integer weight);

    String genNewLine(Item item);
    String genNewLine(Integer weight, String id, Object value);
    String genNewLine(String root, String id, Object value);

    Item createItem(String root, String id, String completeRoot, Integer weight, String rawValue, Object value);
    Item createItem(String completeRoot, String rawValue);
    Item createItem(String completeRoot, Object value);

    String[] splitLine(String line);
    String getIDFromLine(String line);
    String getIDFromCompleteRoot(String completeRoot);
    String getRawValue(String line);
    Object getValue(String line);

    String expandRootToCompleteRootWithLine(String root, String line);
    String expandRootToCompleteRootWithID(String root, String id);
    String cutCompleteRootToRoot(String completeRoot);

    Item simplifyLoadItem(LoadItem loadItem);
    LoadItem extendItem(Item item, String rawLine, String line);
    List<Item> simplifyLoadItemList(List<LoadItem> loadItems);

}
