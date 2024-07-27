package com.framework.application.contracts;


import com.framework.server.router.Router;

import java.util.List;

public interface SettingsInterface {
    void setRouter(Class<? extends Router> router);

    void setRouter(List<Class<? extends Router>> routers);
}
