package com.inet.framework.databases.contracts.table.scheme;

import com.inet.framework.databases.contracts.lambdas.TableSchemeLambda;

public interface TableSchemeInterface {
    /**
     * Get raw data
     * 
     * @return
     */
    public String getDataRaw();

    /**
     * Get table name
     * 
     * @return
     */
    public String getTableName();

    /**
     * Prepare to create table
     * 
     * @param tableName
     * @param schemeLambda
     * @return
     */
    public TableSchemeInterface create(String tableName, TableSchemeLambda schemeLambda);

    /**
     * Prepare to delete table
     * 
     * @param tableName
     * @param schemeLambda
     * @return
     */
    public TableSchemeInterface delete(String tableName, TableSchemeLambda schemeLambda);

    /**
     * Prepare to delete table
     * 
     * @param tableName
     * @param schemeLambda
     * @return
     */
    public TableSchemeInterface delete(String tableName);

}