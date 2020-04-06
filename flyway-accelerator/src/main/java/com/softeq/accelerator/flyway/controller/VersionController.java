package com.softeq.accelerator.flyway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Returns current version info.
 * <p/>
 * Could be used as a health check endpoint.
 * <p/>
 * Created on 4/6/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Slf4j
@RestController
@RequestMapping(value = "/version")
public class VersionController {

    @GetMapping
    public String getVersion() {
        return "0.01";
    }
}
