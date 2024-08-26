package com.framework.databases.Persist;

import com.framework.databases.Model;
import com.framework.databases.Persist.builders.PersistQueryStringBuilder;
import com.framework.databases.contracts.PersistQueryStatementInterface;
import com.framework.databases.contracts.PersistStatementInterface;
import com.framework.databases.contracts.Rawlable;
import com.framework.utils.ExceptionHandler;
import com.framework.utils.Reflect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersistStatement implements PersistStatementInterface {
    private PersistQueryStringBuilder query;
    private final Model model;

    public PersistStatement(Model model) {
        this.model = model;
    }

    private void setQueryString() {
        if (Objects.isNull(query)) {
            query = PersistQueryStringBuilder.build();
        }
    }

    private PersistQueryStatement newPersistQueryStatement() {
        return new PersistQueryStatement(query, model);
    }

    @Override
    public PersistQueryStatementInterface select(String... columns) {
        setQueryString();
        query.select(model.table(), columns);
        return newPersistQueryStatement();
    }

    @Override
    public PersistQueryStatementInterface select() {
        setQueryString();
        query.select(model.table(), "*");
        return newPersistQueryStatement();
    }


    @Override
    public PersistQueryStatementInterface update(List<String> columns, Object data) {
        Persist persist = new Persist();
        Connection connection = persist.open();

        setQueryString();

        List<Object> values = new ArrayList<>();

        columns.forEach((column) -> values.add(Reflect.getFieldValue(data, column, data)));

        query.update(model.table(), columns, values);

        try {
            connection.createStatement().execute(query.getRaw());
        } catch (SQLException exception) {
            ExceptionHandler.print(exception);
        } finally {
            persist.close();
            query.clean();
        }

        return newPersistQueryStatement();
    }

    @Override
    public PersistQueryStatementInterface create(Object data) {
        if (Objects.isNull(data)) return null;

        setQueryString();
        List<String> column = List.of();
        List<Object> values = List.of();

        query.insert(model.table(), column, values);

        return newPersistQueryStatement();
    }

    @Override
    public PersistQueryStatementInterface delete() {
        setQueryString();

        return newPersistQueryStatement();
    }

    public static void simpleExecution(Rawlable rawlable) {
        Persist persist = new Persist();
        Connection connection = persist.open();
        String query = rawlable.getRaw();

        if (Objects.isNull(query)) return;

        try {
            connection.prepareStatement(query).execute();
        } catch (SQLException exception) {
            ExceptionHandler.print(exception);
        } finally {
            persist.close();
        }
    }
}
