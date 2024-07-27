package com.framework.application;

public class Application {
    private final Features features = new Features();
    private final Kernel kernel = Kernel.getInstance();
    public final Settings settings = Settings.getInstance();

    private void initialization() {
        Env env = features.env;

        features.server.configure(env.API_HOST, env.API_PORT);
        kernel.registerRoutesInServer(features.server);
        kernel.automatic.handleAutoInvokeController(features.server);
        features.database.config.set(env.DB_USER, env.DB_PASSWORD, env.DB_HOST, env.DB_PORT, env.DB_DATABASE, env.DB_DRIVES);

    }

    private void finalization() {
        features.server.start();
    }

    public void boot() {
        initialization();
        finalization();
    }

}
