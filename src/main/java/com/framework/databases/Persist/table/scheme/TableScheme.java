package com.framework.databases.Persist.table.scheme;

import com.framework.databases.contracts.lambdas.TableSchemeLambda;
import com.framework.databases.contracts.table.scheme.TableSchemeInterface;

public class TableScheme implements TableSchemeInterface {
    private final TableSchemeColumns tableSchemeColumns = new TableSchemeColumns();
    private String dataRaw;
    private String tableName;

    public static TableScheme build() {
        return new TableScheme();
    }

    private void setTable(String table) {
        this.tableName = table;
    }

    private void setRaw(String data) {
        this.dataRaw = data;
    }

    public TableScheme create(String table, TableSchemeLambda schemeLambda) {
        schemeLambda.execute(tableSchemeColumns);
        setTable(table);
        setRaw("CREATE TABLE IF NOT EXISTS " + tableName + " (" + tableSchemeColumns.getRaw() + ")");
        return this;
    }

    public TableScheme delete(String table, TableSchemeLambda schemeLambda) {
        schemeLambda.execute(tableSchemeColumns);
        setTable(table);
        setRaw("DROP TABLE " + table + " (" + tableSchemeColumns.getRaw() + ")");
        return this;
    }

    public TableSchemeInterface delete(String table) {
        setTable(table);
        setRaw("DROP TABLE " + tableName);
        return this;
    }

    public String getRaw() {
        return dataRaw;
    }

    public String getTableName() {
        return this.tableName;
    }
}
