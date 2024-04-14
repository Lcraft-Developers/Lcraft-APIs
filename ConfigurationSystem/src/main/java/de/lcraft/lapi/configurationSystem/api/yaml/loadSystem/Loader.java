package de.lcraft.lapi.configurationSystem.api.yaml.loadSystem;

import java.io.File;
import java.util.List;

public interface Loader {

    List<LoadItem> load(File file);

    File getFile();
    List<LoadItem> getLoadItems();

}
