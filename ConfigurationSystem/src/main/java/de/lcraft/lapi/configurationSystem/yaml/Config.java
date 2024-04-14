package de.lcraft.lapi.configurationSystem.yaml;

import de.lcraft.lapi.configurationSystem.api.yaml.loadSystem.Loader;
import de.lcraft.lapi.configurationSystem.api.yaml.managment.Item;
import de.lcraft.lapi.configurationSystem.api.yaml.saveSystem.Saver;
import de.lcraft.lapi.configurationSystem.yaml.managment.ConverterCalculator;
import de.lcraft.lapi.javaUtils.ListArrayUtils;
import de.lcraft.lapi.javaUtils.StringUtils;

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
        if(root.contains(".")) {
            String id = ListArrayUtils.makeStringArrayToList(root.split("\\.")).getLast();
            String parentRoot = StringUtils.replaceLast(root, "." + id, "");
            if(!parentRoot.isEmpty() && !exists(parentRoot)) set(parentRoot, "");
        }
        getItems().add(new ConverterCalculator().createItem(root, obj));
    }
    @Override
    public void remove(String root) {
        if(exists(root)) getItems().removeIf(item -> item.getCompleteRoot().startsWith(root));
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
    public void setArray(String root, Object[] objArray) {
        getItems().forEach(item -> {
            if(item.getRoot().equals(root)) remove(item.getCompleteRoot());
        });
        for(int i = 0; i < objArray.length; i++) set(root + "." + i, objArray[i]);
    }
    @Override
    public void setList(String root, List<?> list) {
        getItems().forEach(item -> {
            if(item.getRoot().equals(root)) remove(item.getCompleteRoot());
        });
        for(int i = 0; i < list.size(); i++) set(root + "." + i, list.get(i));
    }
    @Override
    public void setMap(String root, Map<?, ?> map) {
        getItems().forEach(item -> {
            if(item.getRoot().equals(root)) remove(item.getCompleteRoot());
        });
        for(Object key : map.keySet()) {
            Object value = map.get(key);
            set(root + "." + key, value);
        }
    }
    @Override
    public void setSet(String root, Set<?> set) {
        getItems().forEach(item -> {
            if(item.getRoot().equals(root)) remove(item.getCompleteRoot());
        });
        for(int i = 0; i < set.size(); i++) set(root + "." + i, set.toArray()[i]);
    }



    @Override
    public String getString(String root) {
        return String.valueOf(get(root));
    }
    @Override
    public Byte getByte(String root) {
        try { return Byte.parseByte(getString(root)); }
        catch (Exception e) { return null; }
    }
    @Override
    public Short getShort(String root) {
        try { return Short.parseShort(getString(root)); }
        catch (Exception e) { return null; }
    }
    @Override
    public Integer getInt(String root) {
        try { return Integer.parseInt(getString(root)); }
        catch (Exception e) { return null; }
    }
    @Override
    public Double getDouble(String root) {
        try { return Double.parseDouble(getString(root)); }
        catch (Exception e) { return null; }
    }
    @Override
    public Long getLong(String root) {
        try { return Long.parseLong(getString(root)); }
        catch (Exception e) { return null; }
    }
    @Override
    public Float getFloat(String root) {
        try { return Float.parseFloat(getString(root)); }
        catch (Exception e) { return null; }
    }
    @Override
    public Character getChar(String root) {
        try { return getString(root).charAt(0); }
        catch (Exception e) { return null; }
    }
    @Override
    public Boolean getBool(String root) {
        try { return Boolean.parseBoolean(getString(root)); }
        catch (Exception e) { return null; }
    }

    @Override
    public String[] getStringArray(String root) {
        if(!exists(root)) return null;
        if(!exists(root + ".0")) return new String[]{};
        List<String> arrayList = new ArrayList<>();
        for(int i = 0; exists(root + "." + i); i++) arrayList.add(getString(root + "." + i));
        return ListArrayUtils.makeStringListToArray(arrayList);
    }
    @Override
    public Byte[] getByteArray(String root) {
        String[] strArray = getStringArray(root);
        if(strArray == null) return null;
        Byte[] byteArray = new Byte[strArray.length];
        for(int i = 0; i < strArray.length; i++) byteArray[i] = Byte.parseByte(strArray[i]);
        return byteArray;
    }
    @Override
    public Short[] getShortArray(String root) {
        String[] strArray = getStringArray(root);
        if(strArray == null) return null;
        Short[] shortArray = new Short[strArray.length];
        for(int i = 0; i < strArray.length; i++) shortArray[i] = Short.parseShort(strArray[i]);
        return shortArray;
    }
    @Override
    public Integer[] getIntArray(String root) {
        String[] strArray = getStringArray(root);
        if(strArray == null) return null;
        Integer[] intArray = new Integer[strArray.length];
        for(int i = 0; i < strArray.length; i++) intArray[i] = Integer.parseInt(strArray[i]);
        return intArray;
    }
    @Override
    public Double[] getDoubleArray(String root) {
        String[] strArray = getStringArray(root);
        if(strArray == null) return null;
        Double[] doubleArray = new Double[strArray.length];
        for(int i = 0; i < strArray.length; i++) doubleArray[i] = Double.parseDouble(strArray[i]);
        return doubleArray;
    }
    @Override
    public Long[] getLongArray(String root) {
        String[] strArray = getStringArray(root);
        if(strArray == null) return null;
        Long[] longArray = new Long[strArray.length];
        for(int i = 0; i < strArray.length; i++) longArray[i] = Long.parseLong(strArray[i]);
        return longArray;
    }
    @Override
    public Float[] getFloatArray(String root) {
        String[] strArray = getStringArray(root);
        if(strArray == null) return null;
        Float[] floatArray = new Float[strArray.length];
        for(int i = 0; i < strArray.length; i++) floatArray[i] = Float.parseFloat(strArray[i]);
        return floatArray;
    }
    @Override
    public Character[] getCharArray(String root) {
        String[] strArray = getStringArray(root);
        if(strArray == null) return null;
        Character[] charArray = new Character[strArray.length];
        for(int i = 0; i < strArray.length; i++) charArray[i] = strArray[i].charAt(0);
        return charArray;
    }
    @Override
    public Boolean[] getBoolArray(String root) {
        String[] strArray = getStringArray(root);
        if(strArray == null) return null;
        Boolean[] boolArray = new Boolean[strArray.length];
        for(int i = 0; i < strArray.length; i++) boolArray[i] = Boolean.parseBoolean(strArray[i]);
        return boolArray;
    }

    @Override
    public List<?> getList(String root) {
        if(!exists(root)) return null;
        if(!exists(root + ".0")) return new ArrayList<>();
        List<Object> arrayList = new ArrayList<>();
        for(int i = 0; exists(root + "." + i); i++) arrayList.add(get(root + "." + i));
        return arrayList;
    }
    @Override
    public Map<?, ?> getMap(String root) {
        if(!exists(root)) return null;
        Map<String, Object> allItems = new HashMap<>();
        getItems().forEach(item -> {
            if(item.getRoot().equals(root)) allItems.put(item.getID(), item.getValue());
        });
        return allItems;
    }
    @Override
    public Set<?> getSet(String root) {
        if(!exists(root)) return null;
        if(!exists(root + ".0")) return new HashSet<>();
        Set<Object> set = new HashSet<>();
        for(int i = 0; exists(root + "." + i); i++) set.add(get(root + "." + i));
        return set;
    }



    @Override
    public Boolean existsAsString(String root) {
        return getString(root) != null;
    }
    @Override
    public Boolean existsAsByte(String root) {
        return getByte(root) != null;
    }
    @Override
    public Boolean existsAsShort(String root) {
        return getShort(root) != null;
    }
    @Override
    public Boolean existsAsInt(String root) {
        return getInt(root) != null;
    }
    @Override
    public Boolean existsAsDouble(String root) {
        return getDouble(root) != null;
    }
    @Override
    public Boolean existsAsLong(String root) {
        return getLong(root) != null;
    }
    @Override
    public Boolean existsAsFloat(String root) {
        return getFloat(root) != null;
    }
    @Override
    public Boolean existsAsChar(String root) {
        return getChar(root) != null;
    }
    @Override
    public Boolean existsAsBool(String root) {
        return getBool(root) != null;
    }

    @Override
    public Boolean existsAsStringArray(String root) {
        return getStringArray(root) != null && getStringArray(root).length > 0;
    }
    @Override
    public Boolean existsAsByteArray(String root) {
        return getByteArray(root) != null && getByteArray(root).length > 0;
    }
    @Override
    public Boolean existsAsShortArray(String root) {
        return getShortArray(root) != null && getShortArray(root).length > 0;
    }
    @Override
    public Boolean existsAsIntArray(String root) {
        return getIntArray(root) != null && getIntArray(root).length > 0;
    }
    @Override
    public Boolean existsAsDoubleArray(String root) {
        return getDoubleArray(root) != null && getDoubleArray(root).length > 0;
    }
    @Override
    public Boolean existsAsLongArray(String root) {
        return getLongArray(root) != null && getLongArray(root).length > 0;
    }
    @Override
    public Boolean existsAsFloatArray(String root) {
        return getFloatArray(root) != null && getFloatArray(root).length > 0;
    }
    @Override
    public Boolean existsAsCharArray(String root) {
        return getCharArray(root) != null && getCharArray(root).length > 0;
    }
    @Override
    public Boolean existsAsBoolArray(String root) {
        return getByteArray(root) != null && getByteArray(root).length > 0;
    }

    @Override
    public Boolean existsAsList(String root) {
        return getList(root) != null && !getList(root).isEmpty();
    }
    @Override
    public Boolean existsAsMap(String root) {
        return getMap(root) != null && !getMap(root).isEmpty();
    }
    @Override
    public Boolean existsAsSet(String root) {
        return getSet(root) != null && !getSet(root).isEmpty();
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
    public String[] getStringArrayDefault(String root, String[] str) {
        if(!existsAsStringArray(root)) setArray(root, str);
        return getStringArray(root);
    }
    @Override
    public Byte[] getByteArrayDefault(String root, Byte[] obj) {
        if(!existsAsByteArray(root)) setArray(root, obj);
        return getByteArray(root);
    }
    @Override
    public Short[] getShortArrayDefault(String root, Short[] obj) {
        if(!existsAsShortArray(root)) setArray(root, obj);
        return getShortArray(root);
    }
    @Override
    public Integer[] getIntArrayDefault(String root, Integer[] obj) {
        if(!existsAsIntArray(root)) setArray(root, obj);
        return getIntArray(root);
    }
    @Override
    public Double[] getDoubleArrayDefault(String root, Double[] obj) {
        if(!existsAsDoubleArray(root)) setArray(root, obj);
        return getDoubleArray(root);
    }
    @Override
    public Long[] getLongArrayDefault(String root, Long[] obj) {
        if(!existsAsLongArray(root)) setArray(root, obj);
        return getLongArray(root);
    }
    @Override
    public Float[] getFloatArrayDefault(String root, Float[] obj) {
        if(!existsAsFloatArray(root)) setArray(root, obj);
        return getFloatArray(root);
    }
    @Override
    public Character[] getCharArrayDefault(String root, Character[] obj) {
        if(!existsAsCharArray(root)) setArray(root, obj);
        return getCharArray(root);
    }
    @Override
    public Boolean[] getBoolArrayDefault(String root, Boolean[] obj) {
        if(!existsAsBoolArray(root)) setArray(root, obj);
        return getBoolArray(root);
    }

    @Override
    public List<?> getListDefault(String root, List<?> obj) {
        if(!existsAsList(root)) set(root, obj);
        return getList(root);
    }
    @Override
    public Map<?, ?> getMapDefault(String root, Map<?, ?> obj) {
        if(!existsAsMap(root)) set(root, obj);
        return getMap(root);
    }
    @Override
    public Set<?> getSetDefault(String root, Set<?> obj) {
        if(!existsAsSet(root)) set(root, obj);
        return getSet(root);
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

}
