package com.inet.settings;

import java.util.List;
import com.inet.framework.contracts.KernelInterface;
import com.inet.framework.servers.Router;
import com.inet.framework.servers.Server;
import com.inet.framework.utils.Reflect;
import com.inet.routes.PublicRouter;

public class Kernel implements KernelInterface {

    private final List<Class<? extends Router>> routersList = List.of(PublicRouter.class);

    //
    //
    // ###########################
    // ## Kernel Implementation ##
    // ###########################
    //
    //
    private final Server server;

    public Kernel(Server server) {
        this.server = server;
    }

    public void __initialize_routers__() {
        routersList.forEach(router -> {
            Router instance = Reflect.newInstance(router, null);
            instance.setServer(server);
            instance.middlewares_registers();
            instance.registers();
        });
    }

    public void __initialization__() {
        __initialize_routers__();
    }

    public void __finalization__() {
        server.start();
    }

    public void __boot__() {
        __initialization__();
        __finalization__();
    }
}
