package com.framework.databases.contracts;

import java.util.List;

public interface ModelInterface<T> {
    public String table();

    public Class<T> modelData();

    public String[] fields();

    public String primaryKey();

    public T find(String column, Object value);

    public T create(Object data);

    public T update(String column, Object value, Object newData);

    public Boolean delete(String column, Object value);

    public List<T> all();
}
