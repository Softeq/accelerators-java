/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.feign.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;

import java.util.Map;
import java.util.TreeMap;

@Data
public class WeatherResponse {
    private final Map<String, Object> params = new TreeMap<>();

    @JsonAnyGetter
    public Map<String, Object> getParams() {
        return params;
    }

    @JsonAnySetter
    public void setParams(String string, Object ob) {
        params.put(string, ob);
    }
}
