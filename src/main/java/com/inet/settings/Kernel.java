package com.inet.settings;

import java.util.List;
import com.framework.databases.Persist.Persist;
import com.framework.databases.Persist.PersistStatement;
import com.inet.app.databases.migrations.UsersMigrations;
import com.framework.databases.Persist.table.scheme.contracts.KernelInterface;
import com.framework.databases.Persist.table.Migration;
import com.framework.server.router.Router;
import com.framework.server.Server;
import com.framework.utils.Reflect;
import com.inet.app.routes.PublicRouter;

public class Kernel implements KernelInterface {

    //
    //
    // ###########################
    // ##   Kernel Definitions  ##
    // ###########################
    //
    //
    private final List<Class<? extends Router>> routersList = List.of(PublicRouter.class);

    private final List<Class<? extends Migration>> migrations = List.of(UsersMigrations.class);

    //
    //
    // ###########################
    // ## Kernel Implementation ##
    // ###########################
    //
    //
    private final Server server;
    private final Persist persist = Persist.getInstance();

    public Kernel(Server server) {
        this.server = server;
    }

    public void __run_up_migrations__() {
        migrations.forEach((migrationClass) -> {
            Migration migration = Reflect.newInstance(migrationClass, null);

            migration.up();

            migration.getSchemes().forEach(PersistStatement::simpleExecution);
        });
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
        __run_up_migrations__();
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
