package com.inet.settings;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.inet.app.App;
import com.inet.app.databases.migrations.UsersMigrations;
import com.inet.framework.contracts.KernelInterface;
import com.inet.framework.databases.Persist.PersistConnection;
import com.inet.framework.databases.Persist.table.Migration;
import com.inet.framework.databases.contracts.table.scheme.TableSchemeInterface;
import com.inet.framework.servers.Router;
import com.inet.framework.servers.Server;
import com.inet.framework.utils.Reflect;
import com.inet.app.routes.PublicRouter;

public class Kernel implements KernelInterface {

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

    public Kernel(Server server) {
        this.server = server;
    }

    public void __run_up_migrations__() {
        PersistConnection connection = App.mysql.connection();

        connection.open();

        migrations.forEach((migrationClass) -> {
            Migration migration = Reflect.newInstance(migrationClass, null);

            migration.up();

            List<TableSchemeInterface> schemes = migration.getSchemes();

            schemes.forEach((scheme) -> {
                Statement statement = connection.createStatement();

                try {
                    statement.execute(scheme.getDataRaw());
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            });
        });

        connection.open();
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
