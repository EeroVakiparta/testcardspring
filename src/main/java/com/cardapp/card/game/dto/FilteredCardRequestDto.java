package com.cardapp.card.game.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilteredCardRequestDto {
    List<String> toRemove;
    boolean empty;
}
