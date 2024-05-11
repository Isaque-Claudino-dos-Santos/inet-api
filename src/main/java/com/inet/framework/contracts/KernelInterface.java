package com.inet.framework.contracts;

public interface KernelInterface {
    public void __initialize_routers__();

    public void __initialization__();

    public void __finalization__();

    public void __boot__();
}
