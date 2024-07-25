package com.framework.databases.Persist;

import com.framework.databases.Model;
import com.framework.databases.Persist.builders.PersistQueryStringBuilder;
import com.framework.databases.contracts.PersistQueryStatementInterface;
import com.framework.server.HttpClientRequest;
import com.framework.utils.ExceptionHandler;
import com.framework.utils.Reflect;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersistQueryStatement implements PersistQueryStatementInterface {
    public static final String DESC = "desc";
    public static final String ASC = "asc";
    private final Persist persist = Persist.getInstance();
    private final PersistQueryStringBuilder query;
    private final Model model;

    public PersistQueryStatement(PersistQueryStringBuilder query, Model model) {
        this.query = query;
        this.model = model;
    }

    @Override
    public PersistQueryStatementInterface where(String column, String operator, Object value) {
        query.where(column, operator, value);
        return this;
    }

    @Override
    public PersistQueryStatementInterface whereOr(String column, String operator, Object value) {
        query.whereOr(column, operator, value);
        return this;
    }

    @Override
    public PersistQueryStatementInterface limit(Integer value) {
        query.limit(value);
        return this;
    }

    @Override
    public PersistQueryStatementInterface orderByAsc(String column) {
        query.orderBy(column, ASC);
        return this;
    }

    @Override
    public PersistQueryStatementInterface orderByDesc(String column) {
        query.orderBy(column, DESC);
        return this;
    }

    @Override
    public <T> T getById(Object value, Class<T> dataType) {
        T data = Reflect.newInstance(dataType, null);

        query.where(model.primaryKey(), "=", value);

        Connection connection = null;

        try {
            connection = persist.open();

            List<String> columns = query.getSelectedColumns();

            PreparedStatement statement = connection.prepareStatement(query.getRaw(), Statement.RETURN_GENERATED_KEYS);

            statement.execute();

            ResultSet result = statement.getResultSet();

            if (result.next()) {
                if (columns.isEmpty() || columns.getFirst().equals("*")) {
                    columns = Arrays.stream(dataType.getFields()).map(Field::getName).toList();
                }

                for (String column : columns) {
                    Reflect.fieldSetValue(data, column, result.getObject(column));
                }

                return data;
            }
        } catch (SQLException exception) {
            ExceptionHandler.print(exception);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException exception1) {
                ExceptionHandler.print(exception1);
            }
        }

        return null;
    }

    @Override
    public <T> T first(Class<T> dataType) {
        T data = Reflect.newInstance(dataType, null);

        List<String> columns = query.getSelectedColumns();
        Connection connection = null;

        try {
            connection = persist.open();

            PreparedStatement statement = connection.prepareStatement(query.getRaw(), Statement.RETURN_GENERATED_KEYS);

            statement.execute();

            ResultSet result = statement.getResultSet();

            if (result.next()) {
                if (columns.isEmpty() || columns.getFirst().equals("*")) {
                    columns = Arrays.stream(dataType.getFields()).map(Field::getName).toList();
                }

                for (String column : columns) {
                    Reflect.fieldSetValue(data, column, result.getObject(column));
                }
            }

            data = null;
        } catch (SQLException exception) {
            ExceptionHandler.print(exception);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException exception) {
                ExceptionHandler.print(exception);
            }
        }

        return data;
    }

    @Override
    public <T> T last(Class<T> dataType) {
        Connection connection = persist.open();
        T data = Reflect.newInstance(dataType, null);

        List<String> columns = query.getSelectedColumns();

        try {
            PreparedStatement statement = connection.prepareStatement(query.getRaw(), Statement.RETURN_GENERATED_KEYS);

            statement.execute();

            ResultSet result = statement.getResultSet();

            if (columns.isEmpty() || columns.getFirst().equals("*")) {
                columns = Arrays.stream(dataType.getFields()).map(Field::getName).toList();
            }

            while (result.next()) {
                if (result.isLast()) {
                    for (String column : columns) {
                        Reflect.fieldSetValue(data, column, result.getObject(column));
                    }

                    return data;
                }
            }
        } catch (SQLException exception) {
            ExceptionHandler.print(exception);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                ExceptionHandler.print(e);
            }
        }

        return null;
    }

    @Override
    public <T> List<T> all(Class<T> dataType) {
        Connection connection = persist.open();
        ArrayList<T> list = new ArrayList<>();

        List<String> columns = query.getSelectedColumns();

        try {
            PreparedStatement statement = connection.prepareStatement(query.getRaw(), Statement.RETURN_GENERATED_KEYS);

            statement.execute();

            ResultSet result = statement.getResultSet();

            if (columns.isEmpty() || columns.getFirst().equals("*")) {
                columns = Arrays.stream(dataType.getFields()).map(Field::getName).toList();
            }

            while (result.next()) {
                T data = Reflect.newInstance(dataType, null);

                for (String column : columns) {
                    Reflect.fieldSetValue(data, column, result.getObject(column));
                }

                list.add(data);
            }
        } catch (SQLException exception) {
            ExceptionHandler.print(exception);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                ExceptionHandler.print(e);
            }
        }

        return list;
    }

    @Override
    public void exec() {
        Connection connection = persist.open();

        try {
            PreparedStatement statement = connection.prepareStatement(query.getRaw(), Statement.RETURN_GENERATED_KEYS);

            statement.execute();

            ResultSet result = statement.getResultSet();

            while (result.next()) {
                System.out.println(result.getString("name"));
            }
        } catch (SQLException exception) {
            ExceptionHandler.print(exception);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                ExceptionHandler.print(e);


            }
        }
    }


}
