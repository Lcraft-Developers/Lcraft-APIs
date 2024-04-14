package de.lcraft.lapi.configurationSystem.api.yaml.managment;

import java.util.List;

public interface Item {

    void addComments(String... comments);
    void clearComments();

    String getRoot();
    String getID();
    String getCompleteRoot();
    Integer getWeight();

    String getRawValue();
    Object getValue();

    List<String> getComments();

}
