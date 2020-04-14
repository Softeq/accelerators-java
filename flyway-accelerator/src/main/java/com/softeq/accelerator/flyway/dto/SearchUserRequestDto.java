/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Users Search requert
 * <p/>
 * Created on 4/13/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Data
public class SearchUserRequestDto {
    private String firstName;
    private String lastName;
    private String email;

    private int page = 0;
    private int size = 20;
    private String sort;
    private String sortOrder;

    public PageRequest getPageRequest() {

        if (sort == null) {
            return PageRequest.of(page, size);
        }
        Sort.Direction direction = sortOrder == null ? Sort.Direction.ASC : Sort.Direction.valueOf(sortOrder);
        return PageRequest.of(page, size, Sort.by(direction, sort));
    }
}
