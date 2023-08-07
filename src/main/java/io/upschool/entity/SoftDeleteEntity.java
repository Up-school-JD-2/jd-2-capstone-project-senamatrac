package io.upschool.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class SoftDeleteEntity {

    @Column(columnDefinition = "boolean default false",nullable = false)
    private Boolean deleted = Boolean.FALSE;

    @Column
    private LocalDateTime updatedDateTime;
    @Column
    private LocalDateTime deletedDateTime;
}
