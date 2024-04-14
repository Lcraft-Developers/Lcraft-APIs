package de.lcraft.lapi.configurationSystem.yaml.managment;

import java.util.*;

public class Item implements de.lcraft.lapi.configurationSystem.api.yaml.managment.Item {

    private String root;
    private String id;
    private String completeRoot;
    private Integer weight;
    private String rawValue;
    private Object value;
    private List<String> comments;

    public Item(String root, String id, String completeRoot, Integer weight, String rawValue, Object value) {
        init(root, id, completeRoot, weight, rawValue, value);
    }
    private void init(String root, String id, String completeRoot, Integer weight, String rawValue, Object value) {
        if(root != null) setRoot(root);
        if(id != null) setId(id);
        if(completeRoot != null) setCompleteRoot(completeRoot);
        if(weight != null) setWeight(weight);
        if(rawValue != null) setRawValue(rawValue);
        if(value != null) setValue(value);
        setComments(new ArrayList<>());
    }

    @Override
    public void addComments(String... comments) {
        for(String c : comments) getComments().add(c);
    }
    @Override
    public void clearComments() {
        setComments(new ArrayList<>());
    }

    @Override
    public String getRoot() {
        return this.root;
    }
    @Override
    public String getID() {
        return this.id;
    }
    @Override
    public String getCompleteRoot() {
        return this.completeRoot;
    }
    @Override
    public Integer getWeight() {
        return this.weight;
    }
    @Override
    public String getRawValue() {
        return this.rawValue;
    }
    @Override
    public Object getValue() {
        return this.value;
    }
    @Override
    public List<String> getComments() {
        return this.comments;
    }

    private void setRoot(String root) {
        this.root = root;
    }
    private void setId(String id) {
        this.id = id;
    }
    private void setCompleteRoot(String completeRoot) {
        this.completeRoot = completeRoot;
    }
    private void setWeight(Integer weight) {
        this.weight = weight;
    }
    private void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }
    private void setValue(Object value) {
        this.value = value;
    }
    private void setComments(List<String> comments) {
        this.comments = comments;
    }

}
