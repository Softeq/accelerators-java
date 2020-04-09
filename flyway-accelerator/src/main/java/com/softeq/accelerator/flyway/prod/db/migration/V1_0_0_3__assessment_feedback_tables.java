/*
 *
 *  * Developed by Softeq Development Corporation
 *  * http://www.softeq.com
 *
 */

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
public class V1_0_0_3__assessment_feedback_tables extends BaseJavaMigration {
    private static final String SQL = "\n" +
        "CREATE TABLE assessment (\n" +
        "    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
        "    target_user_id int,\n" +
        "    assesment_date datetime\n" +
        ");\n" +
        "\n" +
        "alter table assessment add constraint fk_assessment_users__target_user_id foreign key (target_user_id) references users (id);\n" +
        "\n" +
        "CREATE TABLE feedback (\n" +
        "    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
        "    user_id int,\n" +
        "    assesment_id int,\n" +
        "    feedback_date datetime,\n" +
        "    score decimal(2,2),\n" +
        "    comment varchar (1024)\n" +
        ");\n" +
        "\n" +
        "alter table feedback add constraint fk_feedback_users__user_id foreign key (user_id) references users (id);\n" +
        "alter table feedback add constraint fk_feedback_assessment__assesment_id foreign key (assesment_id) references assessment (id);";

    public void migrate(Context context) throws Exception {
        try (Statement update = context.getConnection().createStatement()) {
            update.execute(SQL);
        }
    }
}
