package com.inet.framework.databases.Persist;

import java.util.ArrayList;
import java.util.List;
import com.inet.framework.databases.contracts.DBQueryInterface;

public class PersistQuery implements DBQueryInterface {
    private String raw = "";

    private String listToString(List<Object> list) {
        List<String> stringList = new ArrayList<>();

        for (Object value : list) {
            if (value instanceof String) {
                stringList.add("'" + value + "'");
            } else {
                stringList.add(value.toString());
            }
        }

        return String.join(", ", stringList);
    }

    @Override
    public DBQueryInterface make() {
        return new PersistQuery();
    }

    @Override
    public DBQueryInterface insert(String table, List<String> columns, List<Object> values) {
        raw = "INSERT INTO " + table;
        raw += " (" + String.join(", ", columns) + ") VALUES (" + listToString(values) + ") ";
        return this;
    }

    @Override
    public DBQueryInterface update(String table, List<String> columns, List<Object> values) {
        raw = "UPDATE " + table + " SET ";
        List<String> set = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            set.add(columns.get(i) + " = " + values.get(i));
        }
        raw += String.join(", ", set) + " ";
        return this;
    }

    @Override
    public DBQueryInterface select(String table, String... columns) {
        raw += "SELECT " + String.join(", ", columns) + " FROM " + table + " ";
        return this;
    }

    @Override
    public DBQueryInterface where(String column, String logic, Object value) {
        raw += "WHERE " + column + " " + logic + " " + value + " ";
        return this;
    }

    @Override
    public DBQueryInterface orderBy(String column, String order) {
        raw += "ORDER BY " + column + " " + order.toUpperCase();
        return this;
    }

    @Override
    public DBQueryInterface groupBy(String column) {
        raw += "GROUP BY " + column;
        return this;
    }

    @Override
    public DBQueryInterface limit(Integer max) {
        raw += "LIMIT " + max;
        return this;
    }

    @Override
    public DBQueryInterface delete(String table) {
        raw += "DELETE FROM " + table + " ";
        return this;
    }

    @Override
    public String getRaw() {
        return raw;
    }
}
