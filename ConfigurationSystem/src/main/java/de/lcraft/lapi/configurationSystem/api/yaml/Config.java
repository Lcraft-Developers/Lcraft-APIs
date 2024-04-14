package de.lcraft.lapi.configurationSystem.api.yaml;

import de.lcraft.lapi.configurationSystem.api.yaml.loadSystem.Loader;
import de.lcraft.lapi.configurationSystem.api.yaml.managment.Item;
import de.lcraft.lapi.configurationSystem.api.yaml.saveSystem.Saver;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * --- Names of every variable ---
 * value - Output as object
 * rawValue - Raw output of the file (of the Loader)
 * <p>
 * root - The root before the line (without ID)
 * id - The id
 * completeRoot - The root and id put together
 * <p>
 * rawLine - The raw output of the file (of the Loader)
 * line - Complete line without spaces
 * <p>
 * The user-created root is the completeRoot
 */

public interface Config {

    void load(File file);
    void save(File file);

    void set(String root, Object obj);
    void remove(String root);
    Object get(String root);
    Object getDefault(String root, Object obj);
    boolean exists(String root);

    void addComments(String root, String... comments);
    void clearComments(String root);



    void setArray(String root, Object[] objArray);
    void setList(String root, List<?> list);
    void setMap(String root, Map<?, ?> map);
    void setSet(String root, Set<?> set);



    String getString(String root);
    Byte getByte(String root);
    Short getShort(String root);
    Integer getInt(String root);
    Double getDouble(String root);
    Long getLong(String root);
    Float getFloat(String root);
    Character getChar(String root);
    Boolean getBool(String root);

    String[] getStringArray(String root);
    Byte[] getByteArray(String root);
    Short[] getShortArray(String root);
    Integer[] getIntArray(String root);
    Double[] getDoubleArray(String root);
    Long[] getLongArray(String root);
    Float[] getFloatArray(String root);
    Character[] getCharArray(String root);
    Boolean[] getBoolArray(String root);

    List<?> getList(String root);
    Map<?, ?> getMap(String root);
    Set<?> getSet(String root);



    Boolean existsAsString(String root);
    Boolean existsAsByte(String root);
    Boolean existsAsShort(String root);
    Boolean existsAsInt(String root);
    Boolean existsAsDouble(String root);
    Boolean existsAsLong(String root);
    Boolean existsAsFloat(String root);
    Boolean existsAsChar(String root);
    Boolean existsAsBool(String root);

    Boolean existsAsStringArray(String root);
    Boolean existsAsByteArray(String root);
    Boolean existsAsShortArray(String root);
    Boolean existsAsIntArray(String root);
    Boolean existsAsDoubleArray(String root);
    Boolean existsAsLongArray(String root);
    Boolean existsAsFloatArray(String root);
    Boolean existsAsCharArray(String root);
    Boolean existsAsBoolArray(String root);

    Boolean existsAsList(String root);
    Boolean existsAsMap(String root);
    Boolean existsAsSet(String root);



    String getStringDefault(String root, String str);
    Byte getByteDefault(String root, Byte obj);
    Short getShortDefault(String root, Short obj);
    Integer getIntDefault(String root, Integer obj);
    Double getDoubleDefault(String root, Double obj);
    Long getLongDefault(String root, Long obj);
    Float getFloatDefault(String root, Float obj);
    Character getCharDefault(String root, Character obj);
    Boolean getBoolDefault(String root, Boolean obj);

    String[] getStringArrayDefault(String root, String[] str);
    Byte[] getByteArrayDefault(String root, Byte[] obj);
    Short[] getShortArrayDefault(String root, Short[] obj);
    Integer[] getIntArrayDefault(String root, Integer[] obj);
    Double[] getDoubleArrayDefault(String root, Double[] obj);
    Long[] getLongArrayDefault(String root, Long[] obj);
    Float[] getFloatArrayDefault(String root, Float[] obj);
    Character[] getCharArrayDefault(String root, Character[] obj);
    Boolean[] getBoolArrayDefault(String root, Boolean[] obj);

    List<?> getListDefault(String root, List<?> obj);
    Map<?, ?> getMapDefault(String root, Map<?, ?> obj);
    Set<?> getSetDefault(String root, Set<?> obj);



    List<Item> getItems();
    Loader getLoader();
    Saver getSaver();

}
