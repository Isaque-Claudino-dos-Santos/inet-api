package com.framework.application;

import com.framework.application.contracts.SettingsInterface;
import com.framework.server.router.Router;
import com.framework.utils.ExceptionHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Settings implements SettingsInterface {
    private static final Kernel kernel = Kernel.getInstance();
    private static Settings instance = null;

    private Settings() {
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }

        return instance;
    }

    @Override
    public void setRouter(Class<? extends Router> router) {
        try {
            Constructor<? extends Router> routerConstructor = router.getDeclaredConstructor();
            Router routerInstance = routerConstructor.newInstance();
            kernel.routers.add(routerInstance);
        } catch (NoSuchMethodException |
                 InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException exception) {
            ExceptionHandler.print(exception);
        }
    }

    @Override
    public void setRouter(List<Class<? extends Router>> routers) {
        routers.forEach(this::setRouter);
    }
}
