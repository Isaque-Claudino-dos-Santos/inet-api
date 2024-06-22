package com.inet.framework.databases.contracts.table.scheme;

public interface TableSchemeColumnsInterface {
    public void id();

    public TableSchemeColumnsInterface string(String column, Integer size);

    public TableSchemeColumnsInterface integer(String column);

    public TableSchemeColumnsInterface autoIncrement();

    public TableSchemeColumnsInterface primaryKey();

    public void timestamp();

    public TableSchemeColumnsInterface nullable();

    public TableSchemeColumnsInterface unique();

    public String getColumnRaw();
}
