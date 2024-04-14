package de.lcraft.lapi.configurationSystem.sql.commands;

import de.lcraft.lapi.configurationSystem.api.sql.SQLCommand;
import de.lcraft.lapi.configurationSystem.api.sql.SQLCommandSender;

import java.util.HashMap;

// Website: https://www.dataquest.io/blog/sql-commands/
public class createTable implements SQLCommand {

    private String tableName;
    private HashMap<Integer, String[]> columnsWithDataTypes;

    public createTable(String tableName, HashMap<Integer, String[]> columnsWithDataTypes) {
        init(tableName, columnsWithDataTypes);
    }
    public createTable(String tableName) {
        init(tableName, null);
    }
    private void init(String tableName, HashMap<Integer, String[]> columnsWithDataTypes) {
        if(tableName != null) setTableName(tableName);
        if(columnsWithDataTypes != null) setColumnsWithDataTypes(columnsWithDataTypes);
        else setColumnsWithDataTypes(new HashMap<>());
    }

    public void addColumn(String name, String type) {
        setColumn(getColumnsWithDataTypes().size(), name, type);
    }
    public void setColumn(Integer index, String name, String type) {
        getColumnsWithDataTypes().put(index, new String[]{name, type});
    }
    public void removeColumn(Integer index) {
        getColumnsWithDataTypes().remove(index);
    }

    @Override
    public String[] createSQL(SQLCommandSender SQLCommandSender) {
        StringBuilder sql = new StringBuilder("CREATE TABLE " + getTableName() + " (");
        for(int index : getColumnsWithDataTypes().keySet()) {
            String[] column = getColumnsWithDataTypes().get(index);
            String name = column[0];
            String type = column[1];
            sql.append(name).append(" ").append(type).append(", ");
        }
        return new String[]{sql + ");"};
    }

    public String getTableName() {
        return tableName;
    }
    public HashMap<Integer, String[]> getColumnsWithDataTypes() {
        return columnsWithDataTypes;
    }

    private void setTableName(String tableName) {
        this.tableName = tableName;
    }
    private void setColumnsWithDataTypes(HashMap<Integer, String[]> columnsWithDataTypes) {
        this.columnsWithDataTypes = columnsWithDataTypes;
    }

}
