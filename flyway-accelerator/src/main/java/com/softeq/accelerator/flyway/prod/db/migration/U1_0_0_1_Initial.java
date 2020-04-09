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
public class U1_0_0_1_Initial extends BaseJavaMigration {
    private static final String SQL = "DROP TABLE users; ";

    public void migrate(Context context) throws Exception {
        try (Statement update = context.getConnection().createStatement()) {
            update.execute(SQL);
        }
    }
}
