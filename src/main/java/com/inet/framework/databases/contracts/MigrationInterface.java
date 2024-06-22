package com.inet.framework.databases.contracts;

import java.util.List;

import com.inet.framework.databases.contracts.table.scheme.TableSchemeInterface;

public interface MigrationInterface {

    /**
     * Instance new table scheme
     * 
     * @return
     */
    public TableSchemeInterface scheme();

    /**
     * Get all schemes
     * 
     * @return
     */
    public List<TableSchemeInterface> getSchemes();

    /**
     * Up tables in database
     */
    public void up();

    /**
     * Destroy table in database
     */
    public void destroy();
}
