package com.framework.databases.contracts;

public interface DBInterface {
    /**
     * Data base connection
     * @return
     */
    public DBConnectionInterface connection();

    /**
     * Make sql query string
     * @return
     */
    public DBQueryInterface query();
}
