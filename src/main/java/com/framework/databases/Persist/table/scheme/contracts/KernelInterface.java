package com.framework.databases.Persist.table.scheme.contracts;

public interface KernelInterface {
    public void __initialize_routers__() throws Exception;

    public void __run_up_migrations__() throws Exception;

    public void __initialization__() throws Exception;

    public void __finalization__() throws Exception;

    public void __boot__() throws Exception;
}
