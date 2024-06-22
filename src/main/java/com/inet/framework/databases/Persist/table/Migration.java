package com.inet.framework.databases.Persist.table;

import java.util.ArrayList;
import java.util.List;

import com.inet.framework.databases.Persist.table.scheme.TableScheme;
import com.inet.framework.databases.contracts.MigrationInterface;
import com.inet.framework.databases.contracts.table.scheme.TableSchemeInterface;

public abstract class Migration implements MigrationInterface {
    private final List<TableSchemeInterface> schemes = new ArrayList<>();

    public TableScheme scheme() {
        TableScheme tableScheme = new TableScheme();
        schemes.add(tableScheme);
        return tableScheme;
    }

    public List<TableSchemeInterface> getSchemes() {
        return schemes;
    }
}
