package io.upschool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class SoftDeleteEntity {

    @Column(columnDefinition = "boolean default false",nullable = false)
    private Boolean isDeleted = Boolean.FALSE;

    @Column
    private LocalDate updatedDate;
}
