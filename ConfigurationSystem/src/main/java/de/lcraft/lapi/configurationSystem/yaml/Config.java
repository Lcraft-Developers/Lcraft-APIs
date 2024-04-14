package de.lcraft.lapi.configurationSystem.yaml;

import de.lcraft.lapi.configurationSystem.api.yaml.loadSystem.Loader;
import de.lcraft.lapi.configurationSystem.api.yaml.managment.Item;
import de.lcraft.lapi.configurationSystem.api.yaml.saveSystem.Saver;
import de.lcraft.lapi.configurationSystem.yaml.managment.ConverterCalculator;

import java.io.File;
import java.util.*;

public class Config implements de.lcraft.lapi.configurationSystem.api.yaml.Config {

    private List<Item> items;
    private Loader loader;
    private Saver saver;

    public Config() {
        init();
    }
    private void init() {
        setItems(new ArrayList<>());
        setLoader(new de.lcraft.lapi.configurationSystem.yaml.loadSystem.Loader());
        setSaver(new de.lcraft.lapi.configurationSystem.yaml.saveSystem.Saver());
    }

    @Override
    public void load(File file) {
        setItems(new de.lcraft.lapi.configurationSystem.yaml.managment.ConverterCalculator().simplifyLoadItemList(getLoader().load(file)));
    }
    @Override
    public void save(File file) {
        getSaver().save(file, getItems());
    }

    @Override
    public void set(String root, Object obj) {
        getItems().add(new ConverterCalculator().createItem(root, obj));
    }
    @Override
    public void remove(String root) {
        if(exists(root)) getItems().removeIf(item -> item.getCompleteRoot().equals(root));
    }
    @Override
    public Object get(String root) {
        for(Item item : getItems()) if(item.getCompleteRoot().equals(root)) return item.getValue();
        return null;
    }
    @Override
    public Object getDefault(String root, Object obj) {
        if(!exists(root)) set(root, obj);
        return get(root);
    }
    @Override
    public boolean exists(String root) {
        return Objects.nonNull(get(root));
    }



    @Override
    public String getString(String root) {
        return "";
    }
    @Override
    public Byte getByte(String root) {
        return 0;
    }
    @Override
    public Short getShort(String root) {
        return 0;
    }
    @Override
    public Integer getInt(String root) {
        return 0;
    }
    @Override
    public Double getDouble(String root) {
        return 0.0;
    }
    @Override
    public Long getLong(String root) {
        return 0L;
    }
    @Override
    public Float getFloat(String root) {
        return 0f;
    }
    @Override
    public Character getChar(String root) {
        return null;
    }
    @Override
    public Boolean getBool(String root) {
        return null;
    }

    @Override
    public String[] getStringArray(String root) {
        return new String[0];
    }
    @Override
    public Byte[] getByteArray(String root) {
        return new Byte[0];
    }
    @Override
    public Short[] getShortArray(String root) {
        return new Short[0];
    }
    @Override
    public Integer[] getIntArray(String root) {
        return new Integer[0];
    }
    @Override
    public Double[] getDoubleArray(String root) {
        return new Double[0];
    }
    @Override
    public Long[] getLongArray(String root) {
        return new Long[0];
    }
    @Override
    public Float[] getFloatArray(String root) {
        return new Float[0];
    }
    @Override
    public Character[] getCharArray(String root) {
        return new Character[0];
    }
    @Override
    public Boolean[] getBoolArray(String root) {
        return new Boolean[0];
    }

    @Override
    public List<?> getList(String root) {
        return List.of();
    }
    @Override
    public Map<?, ?> getMap(String root) {
        return Map.of();
    }
    @Override
    public Set<?> getSet(String root) {
        return Set.of();
    }



    @Override
    public Boolean existsAsString(String root) {
        return null;
    }
    @Override
    public Boolean existsAsByte(String root) {
        return null;
    }
    @Override
    public Boolean existsAsShort(String root) {
        return null;
    }
    @Override
    public Boolean existsAsInt(String root) {
        return null;
    }
    @Override
    public Boolean existsAsDouble(String root) {
        return null;
    }
    @Override
    public Boolean existsAsLong(String root) {
        return null;
    }
    @Override
    public Boolean existsAsFloat(String root) {
        return null;
    }
    @Override
    public Boolean existsAsChar(String root) {
        return null;
    }
    @Override
    public Boolean existsAsBool(String root) {
        return null;
    }

    @Override
    public Boolean existsAsStringArray(String root) {
        return null;
    }
    @Override
    public Boolean existsAsByteArray(String root) {
        return null;
    }
    @Override
    public Boolean existsAsShortArray(String root) {
        return null;
    }
    @Override
    public Boolean existsAsIntArray(String root) {
        return null;
    }
    @Override
    public Boolean existsAsDoubleArray(String root) {
        return null;
    }
    @Override
    public Boolean existsAsLongArray(String root) {
        return null;
    }
    @Override
    public Boolean existsAsFloatArray(String root) {
        return null;
    }
    @Override
    public Boolean existsAsCharArray(String root) {
        return null;
    }
    @Override
    public Boolean existsAsBoolArray(String root) {
        return null;
    }

    @Override
    public Boolean existsAsList(String root) {
        return null;
    }
    @Override
    public Boolean existsAsMap(String root) {
        return null;
    }
    @Override
    public Boolean existsAsSet(String root) {
        return null;
    }



    @Override
    public String getStringDefault(String root, String str) {
        return "";
    }
    @Override
    public Byte getByteDefault(String root, Byte obj) {
        return 0;
    }
    @Override
    public Short getShortDefault(String root, Short obj) {
        return 0;
    }
    @Override
    public Integer getIntDefault(String root, Integer obj) {
        return 0;
    }
    @Override
    public Double getDoubleDefault(String root, Double obj) {
        return 0.0;
    }
    @Override
    public Long getLongDefault(String root, Long obj) {
        return 0L;
    }
    @Override
    public Float getFloatDefault(String root, Float obj) {
        return 0f;
    }
    @Override
    public Character getCharDefault(String root, Character obj) {
        return null;
    }
    @Override
    public Boolean getBoolDefault(String root, Boolean obj) {
        return null;
    }

    @Override
    public String[] getStringArrayDefault(String root, String str) {
        return new String[0];
    }
    @Override
    public Byte[] getByteArrayDefault(String root, Byte obj) {
        return new Byte[0];
    }
    @Override
    public Short[] getShortArrayDefault(String root, Short obj) {
        return new Short[0];
    }
    @Override
    public Integer[] getIntArrayDefault(String root, Integer obj) {
        return new Integer[0];
    }
    @Override
    public Double[] getDoubleArrayDefault(String root, Double obj) {
        return new Double[0];
    }
    @Override
    public Long[] getLongArrayDefault(String root, Long obj) {
        return new Long[0];
    }
    @Override
    public Float[] getFloatArrayDefault(String root, Float obj) {
        return new Float[0];
    }
    @Override
    public Character[] getCharArrayDefault(String root, Character obj) {
        return new Character[0];
    }
    @Override
    public Boolean[] getBoolArrayDefault(String root, Boolean obj) {
        return new Boolean[0];
    }

    @Override
    public List<?> getListDefault(String root, List<?> obj) {
        return List.of();
    }
    @Override
    public Map<?, ?> getMapDefault(String root, Map<?, ?> obj) {
        return Map.of();
    }
    @Override
    public Set<?> getSetDefault(String root, Set<?> obj) {
        return Set.of();
    }



    @Override
    public List<Item> getItems() {
        return this.items;
    }
    @Override
    public Loader getLoader() {
        return this.loader;
    }
    @Override
    public Saver getSaver() {
        return this.saver;
    }

    private void setItems(List<Item> items) {
        this.items = items;
    }
    private void setLoader(Loader loader) {
        this.loader = loader;
    }
    private void setSaver(Saver saver) {
        this.saver = saver;
    }

    /*private List<Item> items;
    private Loader loader;
    private Saver saver;

    public Config() {
        init();
    }
    private void init() {
        setItems(new ArrayList<>());
        setLoader(new de.lcraft.lapi.configurationSystem.yaml.loadSystem.Loader());
        setSaver(new de.lcraft.lapi.configurationSystem.yaml.saveSystem.Saver());
    }

    @Override
    public void load(File file) {
        setItems(new ConverterCalculator().simplifyLoadItemList(getLoader().load(file)));
    }
    @Override
    public void save(File file) {
        getSaver().save(file, getItems());
    }


    @Override
    public void set(String root, Object obj) {
        if(exists(root)) remove(root);
        getItems().add(new de.lcraft.lapi.configurationSystem.yaml.managment.Item(transferToRealRoot(root)[0], transferToRealRoot(root)[1], obj));
    }
    @Override
    public void remove(String root) {
        getItems().removeIf(item -> item.getRoot().equals(transferToRealRoot(root)[0]) && item.getID().equals(transferToRealRoot(root)[1]));
    }
    @Override
    public Object get(String root) {
        for(Item item : getItems()) if(item.getRoot().equals(transferToRealRoot(root)[0]) && item.getID().equals(transferToRealRoot(root)[1])) return item.getValue();
        return null;
    }
    @Override
    public boolean exists(String root) {
        return get(root) != null;
    }
    private String[] transferToRealRoot(String root) {
        String id = ListArrayUtils.makeStringArrayToList(root.split("\\.")).getLast();
        String root_new = StringUtils.replaceLast(root, id, "");
        return new String[]{id, root_new};
    }

    @Override
    public String getString(String root) {
        if(existsAsString(root)) return get(root).toString();
        else return null;
    }
    @Override
    public Byte getByte(String root) {
        if(existsAsByte(root)) return Byte.parseByte(getString(root));
        else return null;
    }
    @Override
    public Short getShort(String root) {
        if(existsAsShort(root)) return Short.parseShort(getString(root));
        else return null;
    }
    @Override
    public Integer getInt(String root) {
        if(existsAsInt(root)) return Integer.parseInt(get(root).toString());
        else return null;
    }
    @Override
    public Double getDouble(String root) {
        if(existsAsDouble(root)) return Double.parseDouble(get(root).toString());
        else return null;
    }
    @Override
    public Long getLong(String root) {
        if(existsAsLong(root)) return Long.parseLong(get(root).toString());
        else return null;
    }
    @Override
    public Float getFloat(String root) {
        if(existsAsFloat(root)) return Float.parseFloat(get(root).toString());
        else return null;
    }
    @Override
    public Character getChar(String root) {
        if(existsAsChar(root)) return getString(root).charAt(0);
        else return null;
    }
    @Override
    public Boolean getBool(String root) {
        if(existsAsBool(root)) return Boolean.parseBoolean(get(root).toString());
        else return null;
    }


    @Override
    public Boolean existsAsString(String root) {
        try {
            if(Objects.nonNull(get(root))) {
                String.valueOf(get(root));
                return true;
            }
        } catch (NumberFormatException nfe) {}
        return false;
    }
    @Override
    public Boolean existsAsByte(String root) {
        try {
            if(Objects.nonNull(get(root))) {
                Byte.parseByte(get(root).toString());
                return true;
            }
        } catch (NumberFormatException nfe) {}
        return false;
    }
    @Override
    public Boolean existsAsShort(String root) {
        try {
            if(Objects.nonNull(get(root))) {
                Short.parseShort(get(root).toString());
                return true;
            }
        } catch (NumberFormatException nfe) {}
        return false;
    }
    @Override
    public Boolean existsAsInt(String root) {
        try {
            if(Objects.nonNull(get(root))) {
                Integer.parseInt(get(root).toString());
                return true;
            }
        } catch (NumberFormatException nfe) {}
        return false;
    }
    @Override
    public Boolean existsAsDouble(String root) {
        try {
            if(Objects.nonNull(get(root))) {
                Double.parseDouble(get(root).toString());
                return true;
            }
        } catch (NumberFormatException nfe) {}
        return false;
    }
    @Override
    public Boolean existsAsLong(String root) {
        try {
            if(Objects.nonNull(get(root))) {
                Long.parseLong(get(root).toString());
                return true;
            }
        } catch (NumberFormatException nfe) {}
        return false;
    }
    @Override
    public Boolean existsAsFloat(String root) {
        try {
            if(Objects.nonNull(get(root))) {
                Float.parseFloat(get(root).toString());
                return true;
            }
        } catch (NumberFormatException nfe) {}
        return false;
    }
    @Override
    public Boolean existsAsChar(String root) {
        try {
            if(Objects.nonNull(get(root))) {
                Character.valueOf(get(root).toString().charAt(0));
                return true;
            }
        } catch (NumberFormatException nfe) {}
        return false;
    }
    @Override
    public Boolean existsAsBool(String root) {
        try {
            if(Objects.nonNull(get(root))) {
                Boolean.parseBoolean(get(root).toString());
                return true;
            }
        } catch (NumberFormatException nfe) {}
        return false;
    }


    @Override
    public String getStringDefault(String root, String str) {
        if(!existsAsString(root)) set(root, str);
        return getString(root);
    }
    @Override
    public Byte getByteDefault(String root, Byte obj) {
        if(!existsAsByte(root)) set(root, obj);
        return getByte(root);
    }
    @Override
    public Short getShortDefault(String root, Short obj) {
        if(!existsAsShort(root)) set(root, obj);
        return getShort(root);
    }
    @Override
    public Integer getIntDefault(String root, Integer obj) {
        if(!existsAsInt(root)) set(root, obj);
        return getInt(root);
    }
    @Override
    public Double getDoubleDefault(String root, Double obj) {
        if(!existsAsDouble(root)) set(root, obj);
        return getDouble(root);
    }
    @Override
    public Long getLongDefault(String root, Long obj) {
        if(!existsAsLong(root)) set(root, obj);
        return getLong(root);
    }
    @Override
    public Float getFloatDefault(String root, Float obj) {
        if(!existsAsFloat(root)) set(root, obj);
        return getFloat(root);
    }
    @Override
    public Character getCharDefault(String root, Character obj) {
        if(!existsAsChar(root)) set(root, obj);
        return getChar(root);
    }
    @Override
    public Boolean getBoolDefault(String root, Boolean obj) {
        if(!existsAsBool(root)) set(root, obj);
        return getBool(root);
    }


    @Override
    public Boolean isEmpty() {
        return getItems().isEmpty();
    }


    @Override
    public List<Item> getItems() {
        return this.items;
    }
    @Override
    public Loader getLoader() {
        return this.loader;
    }
    @Override
    public Saver getSaver() {
        return this.saver;
    }

    private void setItems(List<Item> items) {
        this.items = items;
    }
    private void setLoader(Loader loader) {
        this.loader = loader;
    }
    private void setSaver(Saver saver) {
        this.saver = saver;
    }*/

}
