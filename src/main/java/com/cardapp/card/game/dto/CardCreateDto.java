package com.cardapp.card.game.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class CardCreateDto {
    @NotNull @NotEmpty @Size(min = 5)
    private String name;
    @NotNull @Positive @DecimalMin("1")
    private int value;
    @NotNull @NotEmpty @Size(min = 5)
    private String description;
    @NotEmpty @NotNull @Size(min = 5)
    private String photoUrl;
    @NotEmpty @NotNull @Size(min = 5)
    private String author;
}
