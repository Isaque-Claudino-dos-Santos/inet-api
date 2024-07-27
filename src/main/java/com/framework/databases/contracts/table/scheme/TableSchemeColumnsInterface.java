package com.framework.databases.contracts.table.scheme;

import com.framework.databases.contracts.Rawlable;

public interface TableSchemeColumnsInterface extends Rawlable {
    public void id();

    public TableSchemeColumnsInterface string(String column, Integer size);

    public TableSchemeColumnsInterface integer(String column);

    public TableSchemeColumnsInterface autoIncrement();

    public TableSchemeColumnsInterface primaryKey();

    public void timestamp();

    public TableSchemeColumnsInterface nullable();

    public TableSchemeColumnsInterface unique();
}
