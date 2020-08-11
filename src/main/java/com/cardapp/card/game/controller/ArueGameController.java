package com.cardapp.card.game.controller;

import com.cardapp.card.game.dto.*;
import com.cardapp.card.game.service.impl.ArueGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/aruegame")
public class ArueGameController {

    @Autowired
    private ArueGameService arueGameService;

    @GetMapping("/getallcards")
    public ResponseEntity<CardListResponseDto> getAllCards(final Principal principal){
        return arueGameService.getAllCards(principal);
    }
    @PostMapping("/getrandomcards")
    public ResponseEntity<CardListResponseDto> getRandomCards(@RequestBody(required = false) FilteredCardRequestDto filteredCardRequestDto){
        return arueGameService.getRandomCards(filteredCardRequestDto);
    }
    @PostMapping("/createcard")
    public ResponseEntity<CardResponse> getUserCardsp(Principal principal, @Valid @RequestBody CardCreateDto cardCreateDto){
        return arueGameService.createCard(cardCreateDto,principal);
    }
    @PostMapping("/incrementpoint")
    public ResponseEntity<PointDto> updatePoints(Principal principal){
        return arueGameService.incrementPoint(principal);
    }
    @PostMapping("/getfourrandomcards")
    public ResponseEntity<CardListResponseDto> getFourRandomCards(){
        return arueGameService.getFourRandomCards();
    }
}
