package com.cardapp.card.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Card extends BaseEntity{

    private String name;

    private Integer value;

    private String Description;

    private String photoUrl;

    private String author;
}
