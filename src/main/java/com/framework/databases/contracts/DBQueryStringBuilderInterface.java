package com.framework.databases.contracts;

import java.util.List;

public interface DBQueryStringBuilderInterface extends Rawlable {
    public DBQueryStringBuilderInterface insert(String table, List<String> columns, List<Object>... values);

    public DBQueryStringBuilderInterface update(String table, List<String> columns, List<Object> values);

    public DBQueryStringBuilderInterface select(String table, String... columns);

    public DBQueryStringBuilderInterface where(String column, String logic, Object value);

    public DBQueryStringBuilderInterface whereOr(String column, String logic, Object value);

    public DBQueryStringBuilderInterface orderBy(String column, String order);

    public DBQueryStringBuilderInterface groupBy(String column);

    public DBQueryStringBuilderInterface limit(Integer max);

    public DBQueryStringBuilderInterface delete(String table);

    public void clean();
}