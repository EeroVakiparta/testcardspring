package com.cardapp.card.security.repository.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "roles", indexes = {@Index(name = "IDX_ROLES_NAME", columnList = "name")})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role  implements Serializable {

    private static final long serialVersionUID = -687991492884005033L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

}
