package io.upschool.entity;

import io.upschool.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "seat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Builder
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "seat_code",unique = true)
    private String seatCode;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Column(columnDefinition = "boolean default false")
    private Boolean reserved = Boolean.FALSE;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Aircraft aircraft;
}
