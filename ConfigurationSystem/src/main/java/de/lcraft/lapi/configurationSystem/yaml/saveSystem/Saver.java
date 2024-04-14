package de.lcraft.lapi.configurationSystem.yaml.saveSystem;

import de.lcraft.lapi.configurationSystem.api.yaml.managment.Item;
import de.lcraft.lapi.configurationSystem.api.yaml.managment.ConverterCalculator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Saver implements de.lcraft.lapi.configurationSystem.api.yaml.saveSystem.Saver {

    @Override
    public void save(File file, List<Item> items) {
        SortedMap<String, Item> sortedItems = new TreeMap<>();
        ConverterCalculator converterCalculator = new de.lcraft.lapi.configurationSystem.yaml.managment.ConverterCalculator();

        items.forEach(item -> sortedItems.put(item.getCompleteRoot(), item));
        try {
            PrintWriter printWriter = new PrintWriter(file);

            sortedItems.forEach((s, item) -> printWriter.println(converterCalculator.genNewLine(item)));

            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
