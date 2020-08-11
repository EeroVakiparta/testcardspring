package com.cardapp.card.game.service.impl;

import com.cardapp.card.entity.Card;
import com.cardapp.card.game.dto.*;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;


public interface ArueGameService {
    public ResponseEntity<CardListResponseDto> getAllCards(final Principal principal);

    public ResponseEntity<CardListResponseDto> getRandomCards(final FilteredCardRequestDto dto);

    public ResponseEntity<CardResponse> createCard(final CardCreateDto cardCreateDto,final Principal principal);

    public ResponseEntity<PointDto> incrementPoint(final Principal principal);

    public ResponseEntity<CardListResponseDto> getFourRandomCards();
}
