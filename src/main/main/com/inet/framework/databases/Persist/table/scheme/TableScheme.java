package com.inet.framework.databases.Persist.table.scheme;

import com.inet.framework.databases.contracts.lambdas.TableSchemeLambda;
import com.inet.framework.databases.contracts.table.scheme.TableSchemeInterface;

public class TableScheme implements TableSchemeInterface {
    private String dataRaw;
    private String tableName;
    private final TableSchemeColumns tableSchemeColumns;

    public TableScheme() {
        tableSchemeColumns = new TableSchemeColumns();
    }

    public TableScheme create(String tableName, TableSchemeLambda schemeLambda) {
        schemeLambda.execute(tableSchemeColumns);
        this.tableName = tableName;
        dataRaw = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + tableSchemeColumns.getColumnRaw() + ")";
        return this;
    }

    public TableScheme delete(String tableName, TableSchemeLambda schemeLambda) {
        schemeLambda.execute(tableSchemeColumns);
        this.tableName = tableName;
        dataRaw = "DROP TABLE " + tableName + " (" + tableSchemeColumns.getColumnRaw() + ")";
        return this;
    }

    public TableSchemeInterface delete(String tableName) {
        this.tableName = tableName;
        dataRaw = "DROP TABLE " + tableName;
        return this;
    }

    public String getDataRaw() {
        return dataRaw;
    }

    public String getTableName() {
        return this.tableName;
    }
}
