package com.framework.databases.Persist.builders;

import java.util.ArrayList;
import java.util.List;

import com.framework.databases.contracts.DBQueryStringBuilderInterface;

public class PersistQueryStringBuilder implements DBQueryStringBuilderInterface {
    private String raw = "";
    private List<String> selectedColumns = null;

    private String listToString(List<Object> list) {
        List<String> stringList = new ArrayList<>();

        for (Object value : list) {
            if (value instanceof String) {
                stringList.add("'" + value + "'");
                continue;
            }

            stringList.add(value.toString());
        }

        return String.join(", ", stringList);
    }

    private void concatRaw(String value) {
        raw = raw.concat(value);
    }

    public static PersistQueryStringBuilder build() {
        return new PersistQueryStringBuilder();
    }

    @SafeVarargs
    @Override
    public final DBQueryStringBuilderInterface insert(String table, List<String> columns, List<Object>... values) {
        concatRaw("INSERT INTO " + table + " (");
        concatRaw(String.join(", ", columns) + ") VALUES " + "(");

        for (List<Object> value : values) {
            concatRaw(listToString(value) + ")");
        }

        return this;
    }

    @Override
    public DBQueryStringBuilderInterface update(String table, List<String> columns, List<Object> values) {
        concatRaw("UPDATE " + table + " SET ");

        List<String> set = new ArrayList<>();

        for (int i = 0; i < columns.size(); i++) {
            set.add(columns.get(i) + " = " + values.get(i));
        }

        concatRaw(String.join(", ", set) + " ");
        return this;
    }

    @Override
    public DBQueryStringBuilderInterface select(String table, String... columns) {
        selectedColumns = List.of(columns);
        concatRaw("SELECT " + String.join(", ", columns) + " FROM " + table + " ");
        return this;
    }

    @Override
    public DBQueryStringBuilderInterface where(String column, String logic, Object value) {
        if (raw.contentEquals("where")) {
            concatRaw("AND " + column + " " + logic + " " + value + " ");
            return this;
        }

        concatRaw("WHERE " + column + " " + logic + " " + value + " ");
        return this;
    }

    @Override
    public DBQueryStringBuilderInterface whereOr(String column, String logic, Object value) {
        if (raw.contentEquals("where")) {
            concatRaw("OR " + column + " " + logic + " " + value + " ");
            return this;
        }

        concatRaw("WHERE " + column + " " + logic + " " + value + " ");
        return this;
    }

    @Override
    public DBQueryStringBuilderInterface orderBy(String column, String order) {
        if (raw.contentEquals("ORDER BY")) {
            concatRaw(", " + column + " " + order.toUpperCase());
            return this;
        }

        concatRaw("ORDER BY " + column + " " + order.toUpperCase());
        return this;
    }

    @Override
    public DBQueryStringBuilderInterface groupBy(String column) {
        concatRaw("GROUP BY " + column);
        return this;
    }

    @Override
    public DBQueryStringBuilderInterface limit(Integer max) {
        concatRaw("LIMIT " + max);
        return this;
    }

    @Override
    public DBQueryStringBuilderInterface delete(String table) {
        concatRaw("DELETE FROM " + table + " ");
        return this;
    }

    @Override
    public String getRaw() {
        return raw;
    }

    @Override
    public void clean() {
        raw = "";
        selectedColumns = null;
    }

    public Boolean isSelectAll() {
        return selectedColumns != null && selectedColumns.getFirst().equals("*");
    }

    public List<String> getSelectedColumns() {
        return this.selectedColumns;
    }
}
