package com.inet.framework.databases.contracts.lambdas;

import com.inet.framework.databases.contracts.table.scheme.TableSchemeColumnsInterface;

public interface TableSchemeLambda {
    public void execute(TableSchemeColumnsInterface column);
    
}
