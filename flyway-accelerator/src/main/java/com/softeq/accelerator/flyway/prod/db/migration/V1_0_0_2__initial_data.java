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
public class V1_0_0_2__initial_data extends BaseJavaMigration {
    private static final String SQL = "insert into users\n" +
        "(first_name, last_name, email, status)\n" +
        "values\n" +
        "('${defaultUser}', '${defaultUser}', 'default.admin@default.admin.com', 'ACTIVE');";

    public void migrate(Context context) throws Exception {
        try (Statement update = context.getConnection().createStatement()) {
            update.execute(SQL);
        }
    }
}
