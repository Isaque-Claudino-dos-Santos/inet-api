package com.framework.database.persist;

import com.framework.databases.Model;
import com.framework.databases.Persist.Persist;
import com.framework.databases.Persist.PersistStatement;
import com.framework.databases.Persist.table.Migration;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PersistTest {
    public static Faker faker = new Faker();
    public static Persist persist;
    public static Migration migration;
    public static Model user;

    @BeforeAll
    static void configure_database() {
        persist = Persist.getInstance();

        migration = new Migration() {
            @Override
            public void up() {
                scheme().create("users", (column) -> {
                    column.string("name", 20);
                });
            }

            @Override
            public void destroy() {

            }
        };

        user = new Model() {
            @Override
            public String table() {
                return "users";
            }

            @Override
            public List<String> columns() {
                return List.of("name");
            }
        };

        migration.up();

        migration.getSchemes().forEach(PersistStatement::simpleExecution);
    }

    public static class NewUser {
        public String name = faker.name().firstName();
    }

    @Test
    void it_should_insert_new_user() {
        user.statement().create(new NewUser());
    }
}
