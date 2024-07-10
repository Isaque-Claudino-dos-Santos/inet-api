package com.framework.databases.contracts;

import java.util.List;

public interface DBQueryInterface {

    /**
     * Prepare to create new database query
     * @return
     */
    public DBQueryInterface make();

    public DBQueryInterface insert(String table, List<String> columns, List<Object> values);
    
    public DBQueryInterface update(String table, List<String> columns, List<Object> values);

    public DBQueryInterface select(String table, String... columns);

    public DBQueryInterface where(String column, String logic, Object value);

    public DBQueryInterface orderBy(String column, String order);

    public DBQueryInterface groupBy(String column);

    public DBQueryInterface limit(Integer max);

    public DBQueryInterface delete(String table);

    public String getRaw();
}