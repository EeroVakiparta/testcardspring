package com.cardapp.card.game.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponse {
     String name;

     Integer value;

     String Description;

     String photoUrl;

     String author;
}
