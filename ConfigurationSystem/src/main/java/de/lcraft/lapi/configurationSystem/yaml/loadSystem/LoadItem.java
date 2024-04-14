package de.lcraft.lapi.configurationSystem.yaml.loadSystem;

import de.lcraft.lapi.configurationSystem.yaml.managment.Item;

public class LoadItem extends Item implements de.lcraft.lapi.configurationSystem.api.yaml.loadSystem.LoadItem {

    private String rawLine;
    private String line;

    public LoadItem(String root, String id, String completeRoot, Integer weight, String rawValue, Object value, String rawLine, String line) {
        super(root, id, completeRoot, weight, rawValue, value);
        init(rawLine, line);
    }
    private void init(String rawLine, String line) {
        if(rawLine != null) setRawLine(rawLine);
        if(line != null) setLine(line);
    }

    @Override
    public String getRawLine() {
        return this.rawLine;
    }
    @Override
    public String getLine() {
        return this.line;
    }

    private void setRawLine(String rawLine) {
        this.rawLine = rawLine;
    }
    private void setLine(String line) {
        this.line = line;
    }

}
