package com.framework.application;

import com.framework.server.Server;
import com.framework.server.router.Router;
import java.util.ArrayList;

class Kernel {
    private static Kernel instance = null;
    public final ArrayList<Router> routers = new ArrayList<>();
    public final Automatic automatic = new Automatic();

    private Kernel() {
    }

    public static Kernel getInstance() {
        if (instance == null) {
            instance = new Kernel();
        }

        return instance;
    }

    public void registerRoutesInServer(Server server) {
        routers.forEach(Router::registerRouterInServer);
    }
}
