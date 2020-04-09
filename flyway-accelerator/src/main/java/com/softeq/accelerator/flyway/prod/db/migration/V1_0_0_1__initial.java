package com.softeq.accelerator.flyway.prod.db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Statement;

/**
 * Shows Java based migrations
 * <p/>
 * Created on 4/9/2020.
 * <p/>
 *
 * @author slapitsky
 */
public class V1_0_0_1__initial extends BaseJavaMigration {
    private static final String SQL = "CREATE TABLE users (\n" +
        "    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
        "    first_name varchar(50),\n" +
        "    last_name varchar(50),\n" +
        "    email varchar(50) unique not null,\n" +
        "    status varchar(50) not null default 'ACTIVE'\n" +
        ");";

    public void migrate(Context context) throws Exception {
        try (Statement update = context.getConnection().createStatement()) {
            update.execute(SQL);
        }
    }
}
