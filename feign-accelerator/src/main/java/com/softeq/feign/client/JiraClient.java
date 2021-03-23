/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.feign.client;

import com.softeq.feign.configuration.JiraClientConfig;
import com.softeq.feign.model.JiraResponse;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "jira", url = "https://jira.softeq.com", configuration = JiraClientConfig.class)
public interface JiraClient {
    @RequestLine("GET /browse")
    ResponseEntity<JiraResponse> getInfoFromJqlRequest(@RequestParam(name = "jql", required = false) String jqlRequest);

    @RequestLine("GET /projects/{project}/issues")
    ResponseEntity<JiraResponse> getIssues(@RequestParam(name = "filter", required = false) String filter,
                                                    @PathVariable(name = "project") String project);

    @RequestLine("GET /secure/RapidBoard.jspa")
    ResponseEntity<JiraResponse> getActiveSprints(@RequestParam(name = "rapidView", required = false) String rapidView);
}
