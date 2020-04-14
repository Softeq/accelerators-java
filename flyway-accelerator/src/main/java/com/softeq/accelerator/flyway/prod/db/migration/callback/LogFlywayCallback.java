/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.prod.db.migration.callback;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.callback.BaseCallback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;

/**
 * Migration callback example to log migration version after applying
 * <p/>
 * Created on 4/9/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Slf4j
public class LogFlywayCallback extends BaseCallback {

    @Override
    public void handle(Event event, Context context) {
        if (Event.AFTER_EACH_MIGRATE == event && context.getMigrationInfo() != null) {
            log.info("Migration applied, version:{}", context.getMigrationInfo().getVersion());
        }
    }

}