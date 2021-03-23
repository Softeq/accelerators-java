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
public class JiraResponse {
    private final Map<String, Object> issues = new TreeMap<>();

    @JsonAnyGetter
    public Map<String, Object> getIssues() {
        return issues;
    }

    @JsonAnySetter
    public void setIssues(String string, Object ob) {
        issues.put(string, ob);
    }
}
