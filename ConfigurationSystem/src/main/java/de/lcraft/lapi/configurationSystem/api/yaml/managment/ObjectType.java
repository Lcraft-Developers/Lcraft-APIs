package de.lcraft.lapi.configurationSystem.api.yaml.managment;

public enum ObjectType {

    UNDEFINED(Object.class, "obj"),
    STRING(String.class, "str"),

    BYTE(Byte.class, "byte"),
    SHORT(Short.class, "short"),
    INTEGER(Integer.class, "int"),
    DOUBLE(Double.class, "double"),
    LONG(Long.class, "long"),
    FLOAT(Float.class, "float"),
    CHAR(Character.class, "char"),
    BOOLEAN(Boolean.class, "bool");

    private Class<?> classType;
    private String id;

    ObjectType(Class<?> objectClass, String id) {
        init(objectClass, id);
    }
    private void init(Class<?> objectClass, String id) {
        if(objectClass != null) setClassType(objectClass);

        if(id != null) setId(id);
    }

    public Class<?> getClassType() {
        return classType;
    }
    public String getId() {
        return id;
    }

    private void setClassType(Class<?> classType) {
        this.classType = classType;
    }
    private void setId(String id) {
        this.id = id;
    }

}
