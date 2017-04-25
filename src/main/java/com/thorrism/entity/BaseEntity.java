package com.thorrism.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Hercules on 4/23/2017.
 */
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    protected Long id;

    public Long getId() {
        return id;
    }
}
