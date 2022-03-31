package com.oze.kelechi_oze.io.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Staff {

    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    @CreationTimestamp
    private Date registrationDate;

}
