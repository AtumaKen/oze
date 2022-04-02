package com.oze.kelechi_oze.io.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "STAFF")
public class Staff {

    @Id
    @GeneratedValue
    private Long id;
    @Type(type="uuid-char")
    private UUID uuid;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @CreationTimestamp
    @Column(name = "REGISTRATIONDATE")
    private Date registrationDate;

}
