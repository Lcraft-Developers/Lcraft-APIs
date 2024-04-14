package de.lcraft.lapi.configurationSystem.yaml.loadSystem;

import de.lcraft.lapi.configurationSystem.api.yaml.loadSystem.LoadItem;
import de.lcraft.lapi.configurationSystem.api.yaml.managment.ConverterCalculator;
import de.lcraft.lapi.javaUtils.FileUtils;
import de.lcraft.lapi.javaUtils.ListArrayUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loader implements de.lcraft.lapi.configurationSystem.api.yaml.loadSystem.Loader {

    private File file;
    private List<LoadItem> loadItems;

    @Override
    public List<LoadItem> load(File file) {
        setLoadItems(new ArrayList<>());

        List<String> allLines;
        List<String> linesBefore = new ArrayList<>();
        List<String> currentComments = new ArrayList<>();
        ConverterCalculator converterCalculator = new de.lcraft.lapi.configurationSystem.yaml.managment.ConverterCalculator();

        try { allLines = FileUtils.getAllLinesFromFile(file); }
        catch(IOException e) { throw new RuntimeException(e); }

        for(String rawLine : allLines) {
            if(!converterCalculator.isLineComment(rawLine)) {
                LoadItem loadItem = converterCalculator.convertRawLineToLoadItem(rawLine, ListArrayUtils.makeStringListToArray(linesBefore));

                if(!currentComments.isEmpty()) loadItem.addComments(ListArrayUtils.makeStringListToArray(currentComments));
                currentComments.clear();

                getLoadItems().add(loadItem);
                linesBefore.add(rawLine);
            } else currentComments.add(converterCalculator.convertRawLineToLine(rawLine.replaceFirst(de.lcraft.lapi.configurationSystem.yaml.managment.ConverterCalculator.getCommentIndicator() + " ", "")));
        }

        return getLoadItems();
    }

    @Override
    public File getFile() {
        return this.file;
    }
    @Override
    public List<LoadItem> getLoadItems() {
        return this.loadItems;
    }

    private void setFile(File file) {
        this.file = file;
    }
    private void setLoadItems(List<LoadItem> loadItems) {
        this.loadItems = loadItems;
    }

}
