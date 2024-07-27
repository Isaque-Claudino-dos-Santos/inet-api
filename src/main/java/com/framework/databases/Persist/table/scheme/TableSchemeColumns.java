package com.framework.databases.Persist.table.scheme;

import java.util.ArrayList;
import java.util.List;

import com.framework.databases.contracts.table.scheme.TableSchemeColumnsInterface;

public class TableSchemeColumns implements TableSchemeColumnsInterface {
    private final List<String> columns = new ArrayList<>();
    private String lastColumn = null;

    public void id() {
        columns.add("id INT AUTO_INCREMENT PRIMARY KEY NOT NULL");
    }

    public TableSchemeColumns string(String column, Integer size) {
        lastColumn = column;
        columns.add(column + " VARCHAR(" + size + ") NOT NULL");
        return this;
    }

    public TableSchemeColumns integer(String column) {
        lastColumn = column;
        columns.add(column + " INT NOT NULL");
        return this;
    }

    public TableSchemeColumns autoIncrement() {
        String column = columns.getLast();
        columns.removeLast();
        columns.add(column + " AUTO_INCREMENT");
        return this;
    }

    public TableSchemeColumns primaryKey() {
        String column = columns.getLast();
        columns.removeLast();
        columns.add(column + " PRIMARY KEY");
        return this;
    }

    @Override
    public void timestamp() {
        columns.add("created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP");
        columns.add("updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP");
    }

    public TableSchemeColumns nullable() {
        String column = columns.getLast();
        columns.removeLast();
        columns.add(column.replace(" NOT NULL", ""));
        return this;
    }

    @Override
    public TableSchemeColumnsInterface unique() {
        columns.add("UNIQUE (" + lastColumn + ") ");
        return this;
    }

    @Override
    public String getRaw() {
        return String.join(", ", columns);
    }
}
