/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Custom actuator endpoint.
 * <p/>
 * Returns addition application information.
 * <p/>
 * Created on 4/15/2020.
 * <p/>
 *
 * @author amilosh
 */
@Component
@Endpoint(id = "app-info")
public class AppActuatorEndpoint {

    public static final String APP_VERSION = "appVersion";

    @Value("${info.app.version}")
    private String appVersion;

    @ReadOperation
    public Map<String, Object> appInfo() {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put(APP_VERSION, appVersion);
        return details;
    }
}
