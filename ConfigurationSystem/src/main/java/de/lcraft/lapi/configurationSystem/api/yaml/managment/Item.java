package de.lcraft.lapi.configurationSystem.api.yaml.managment;

public interface Item {

    String getRoot();
    String getID();
    String getCompleteRoot();
    Integer getWeight();

    String getRawValue();
    Object getValue();

}
