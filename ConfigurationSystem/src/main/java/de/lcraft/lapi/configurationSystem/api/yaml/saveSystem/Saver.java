package de.lcraft.lapi.configurationSystem.api.yaml.saveSystem;

import de.lcraft.lapi.configurationSystem.api.yaml.managment.Item;
import java.io.File;
import java.util.List;

public interface Saver {

    void save(File file, List<Item> items);

}
