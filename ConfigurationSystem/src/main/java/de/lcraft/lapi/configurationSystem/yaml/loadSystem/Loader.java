package de.lcraft.lapi.configurationSystem.yaml.loadSystem;

import de.lcraft.lapi.configurationSystem.api.yaml.loadSystem.LoadItem;
import de.lcraft.lapi.configurationSystem.yaml.managment.ConverterCalculator;
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

        try { allLines = FileUtils.getAllLinesFromFile(file); }
        catch(IOException e) { throw new RuntimeException(e); }

        for(String rawLine : allLines) {
            getLoadItems().add(new ConverterCalculator().convertRawLineToLoadItem(rawLine, ListArrayUtils.makeStringListToArray(linesBefore)));
            linesBefore.add(rawLine);
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
