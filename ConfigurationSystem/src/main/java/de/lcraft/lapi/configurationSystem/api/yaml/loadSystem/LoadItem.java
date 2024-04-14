package de.lcraft.lapi.configurationSystem.api.yaml.loadSystem;

import de.lcraft.lapi.configurationSystem.api.yaml.managment.Item;

public interface LoadItem extends Item {

    String getRawLine();
    String getLine();

}
