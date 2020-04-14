/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.dto;


/**
 * Represent page implementation
 * <p/>
 * Created on 4/13/2020.
 * <p/>
 *
 * @author slapitsky
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;

@JsonIgnoreProperties(value = "pageable", ignoreUnknown = true)
public class PageDto<T> extends PageImpl<T> implements Page<T> {

    @JsonCreator
    public PageDto() {
        super(new ArrayList<>());
    }
}

