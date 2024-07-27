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

public class PersistStatement implements PersistStatementInterface {
    private final Persist persist = Persist.getInstance();
    private PersistQueryStringBuilder query = null;
    private final Model model;

    public PersistStatement(Model model) {
        this.model = model;
    }

    private void setQueryString() {
        if (query == null) {
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
        setQueryString();

        List<Object> values = new ArrayList<>();

        for (String column : columns) {
            values.add(Reflect.getFieldValue(data, column, data));
        }

        query.update(model.table(), columns, values);

        try (Connection connection = persist.open()) {

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
        if (data == null) return null;

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
        Persist persist = Persist.getInstance();
        String query = rawlable.getRaw();

        if (persist == null || query == null) return;

        Connection connection = null;

        try {
            connection = persist.open();
            connection.prepareStatement(query).execute();
        } catch (SQLException exception) {
            ExceptionHandler.print(exception);

            try {
                connection.close();
            } catch (SQLException exception1) {
                ExceptionHandler.print(exception1);
            }
        }
    }


}
