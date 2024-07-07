package com.framework.contracts;

public interface KernelInterface {
    public void __initialize_routers__();

    public void __run_up_migrations__();

    public void __initialization__();

    public void __finalization__();

    public void __boot__();
}
