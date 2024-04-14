package de.lcraft.lapi.configurationSystem.sql.commands;

import de.lcraft.lapi.configurationSystem.api.sql.SQLCommand;
import de.lcraft.lapi.configurationSystem.api.sql.SQLCommandSender;

// https://www.dataquest.io/blog/sql-commands/
public class _templateW_tableName implements SQLCommand {

    private String tableName;

    public _templateW_tableName(String tableName) {
        init(tableName);
    }
    private void init(String tableName) {
        if(tableName != null) setTableName(tableName);
    }
    @Override
    public String[] createSQL(SQLCommandSender SQLCommandSender) {
        return new String[]{};
    }

    public String getTableName() {
        return tableName;
    }
    private void setTableName(String tableName) {
        this.tableName = tableName;
    }

}
