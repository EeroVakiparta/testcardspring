package com.cardapp.card.game.service.impl;

import com.cardapp.card.entity.Card;
import com.cardapp.card.game.dto.CardCreateDto;
import com.cardapp.card.game.dto.CardListResponseDto;
import com.cardapp.card.game.dto.CardResponse;
import com.cardapp.card.game.dto.PointDto;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.security.Principal;


public interface ArueGameService {
    public ResponseEntity<CardListResponseDto> getAllCards(final Principal principal);

    public ResponseEntity<CardListResponseDto> getRandomCards(final int number);

    public ResponseEntity<CardResponse> createCard(final CardCreateDto cardCreateDto,final Principal principal);

    public ResponseEntity<PointDto> incrementPoint(final Principal principal);
}
