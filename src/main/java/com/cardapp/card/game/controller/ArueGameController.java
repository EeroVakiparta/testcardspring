package com.cardapp.card.game.controller;

import com.cardapp.card.game.dto.CardCreateDto;
import com.cardapp.card.game.dto.CardListResponseDto;
import com.cardapp.card.game.dto.CardResponse;
import com.cardapp.card.game.dto.PointDto;
import com.cardapp.card.game.service.impl.ArueGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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
    @GetMapping("/getrandomcards")
    public ResponseEntity<CardListResponseDto> getRandomCards(@RequestParam(required = false,defaultValue = "4") int number){
        return arueGameService.getRandomCards(number);
    }
    @PostMapping("/createcard")
    public ResponseEntity<CardResponse> getUserCardsp(Principal principal, @Valid @RequestBody CardCreateDto cardCreateDto){
        return arueGameService.createCard(cardCreateDto,principal);
    }
    @PostMapping("/incrementpoint")
    public ResponseEntity<PointDto> updatePoints(Principal principal){
        return arueGameService.incrementPoint(principal);
    }
}
