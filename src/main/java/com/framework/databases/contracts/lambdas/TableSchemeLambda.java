package com.framework.databases.contracts.lambdas;

import com.framework.databases.contracts.table.scheme.TableSchemeColumnsInterface;

public interface TableSchemeLambda {
    public void execute(TableSchemeColumnsInterface column);
    
}
