package com.framework.databases;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.framework.databases.Persist.Persist;
import com.framework.databases.Persist.PersistConnection;
import com.inet.app.App;
import com.framework.databases.contracts.ModelInterface;
import com.framework.utils.Reflect;

public abstract class Model<T> implements ModelInterface<T> {
    private final Persist persist = App.mysql;

    @Override
    public T find(String column, Object value) {
        String query = persist.query().make().select(table(), fields()).where(column, "=", value).getRaw();
        T modelData = Reflect.newInstance(modelData(), null);

        PersistConnection connection = persist.connection();

        connection.open();

        try {
            ResultSet result = connection.createStatement().executeQuery(query);

            if (result.next()) {
                for (String field : fields()) {
                    Object fieldValue = result.getObject(field);
                    Reflect.fieldSetValue(modelData, field, fieldValue);
                }
            } else {
                return null;
            }

            result.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        connection.close();

        return modelData;
    }

    @Override
    public T create(Object data) {
        T modelData = Reflect.newInstance(modelData(), null);
        PersistConnection connection = persist.connection();

        connection.open();

        try {

            Statement statement = connection.createStatement();

            List<String> keys = new ArrayList<>();
            List<Object> values = new ArrayList<>();

            for (Field field : Reflect.getFields(data)) {
                String name = field.getName();
                Object value = field.get(data);

                Reflect.fieldSetValue(modelData, name, value);

                keys.add(name);
                values.add(value);
            }

            String query = persist.query().make().insert(table(), keys, values).getRaw();

            statement.execute(query);

            statement.close();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        connection.close();

        return modelData;
    }

    @Override
    public Boolean delete(String column, Object value) {
        PersistConnection connection = persist.connection();

        connection.open();

        try {
            Statement statement = connection.createStatement();

            String query = persist.query().make().delete(table()).where(column, "=", value).getRaw();

            statement.execute(query);

            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        connection.close();

        return true;

    }

    @Override
    public T update(String column, Object value, Object newData) {
        T modelData = Reflect.newInstance(modelData(), null);
        PersistConnection connection = persist.connection();

        connection.open();

        try {
            Statement statement = connection.createStatement();

            List<String> keys = new ArrayList<>();
            List<Object> values = new ArrayList<>();

            for (Field field : Reflect.getFields(newData)) {
                String name = field.getName();
                Object fieldValue = field.get(newData);

                Reflect.fieldSetValue(modelData, name, fieldValue);

                keys.add(name);
                values.add(fieldValue);
            }

            String query = persist.query().make().update(table(), keys, values).where(column, "=", value).getRaw();

            statement.execute(query);

            statement.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return modelData;
    }

    public List<T> all() {
        List<T> data = new ArrayList<>();
        PersistConnection connection = persist.connection();

        connection.open();

        try {
            Statement statement = connection.createStatement();

            String query = persist.query().make().select(table(), fields()).getRaw();

            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                T modelData = Reflect.newInstance(modelData(), null);

                for (String field : fields()) {
                    Object fieldValue = result.getObject(field);
                    Reflect.fieldSetValue(modelData, field, fieldValue);
                }

                data.add(modelData);
            }

            result.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        connection.close();

        return data;
    }

    @Override
    public String primaryKey() {
        return "id";
    }

    public abstract String table();

    public abstract Class<T> modelData();

    public abstract String[] fields();
}
