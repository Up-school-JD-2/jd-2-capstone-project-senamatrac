package io.upschool.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SoftDeleteEntity {
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @Column
    private LocalDate updatedDate;
}
