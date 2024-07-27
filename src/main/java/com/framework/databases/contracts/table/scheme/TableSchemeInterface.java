package com.framework.databases.contracts.table.scheme;

import com.framework.databases.contracts.Rawlable;
import com.framework.databases.contracts.lambdas.TableSchemeLambda;

public interface TableSchemeInterface extends Rawlable {
    /**
     * Get table name
     */
    public String getTableName();

    /**
     * Prepare to create table
     */
    public TableSchemeInterface create(String tableName, TableSchemeLambda schemeLambda);

    /**
     * Prepare to delete table
     */
    public TableSchemeInterface delete(String tableName, TableSchemeLambda schemeLambda);

    /**
     * Prepare to delete table
     */
    public TableSchemeInterface delete(String tableName);

}