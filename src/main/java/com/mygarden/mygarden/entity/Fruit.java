package com.mygarden.mygarden.entity;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Calendar;

@Entity
@Table(name="fruit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique = true)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable=false,updatable=false)
    @CreationTimestamp
    private Calendar dateCreate;
}
