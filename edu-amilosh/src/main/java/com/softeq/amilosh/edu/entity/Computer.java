/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.amilosh.edu.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represents Computer from DB.
 * <p/>
 * Created on 2021-03-24
 * <p/>
 *
 * @author Alexander Milosh
 */
@Entity
@Table(name = "computer")
public class Computer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "inventory_number")
    private Integer inventoryNumber;

    @OneToOne(mappedBy = "computer", cascade = CascadeType.ALL, orphanRemoval = true)
    private WorkPlace workPlace;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Integer inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public WorkPlace getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(WorkPlace workPlace) {
        this.workPlace = workPlace;
    }
}
