package com.cardapp.card.game.service.impl;

import com.cardapp.card.entity.Card;
import com.cardapp.card.exception.ServiceException;
import com.cardapp.card.exception.constant.ErrorCodeEnum;
import com.cardapp.card.game.dto.*;
import com.cardapp.card.game.repository.CardRepository;
import com.cardapp.card.security.repository.UserRepository;
import com.cardapp.card.security.repository.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Principal;
import java.util.*;

@Service
public class ArueGameServiceImpl implements ArueGameService{
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<CardListResponseDto> getAllCards(Principal principal) {

        final String username = principal.getName();

        final Optional<User> user = userRepository.findByUsername(username);

        return new ResponseEntity<>(
                modelMapper.map(user.get(),CardListResponseDto.class)
                ,HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<CardListResponseDto> getRandomCards(final FilteredCardRequestDto dto) {
            if(dto.isEmpty()){
                final List<Card> cardList = cardRepository.getRandomCards();
                final List<CardResponse> cardResponseList = new LinkedList<>();
                for (Card card : cardList) {
                    cardResponseList.add(modelMapper.map(card, CardResponse.class));
                }
                final CardListResponseDto cardListResponseDto = new CardListResponseDto();
                cardListResponseDto.setCards(cardResponseList);
                return new ResponseEntity(cardListResponseDto, HttpStatus.OK);

            }
            if(dto.getToRemove().size() == 0) {
                final CardListResponseDto cardListResponseDto = new CardListResponseDto();
                cardListResponseDto.setCards(new ArrayList<>());
                return new ResponseEntity<>(cardListResponseDto, HttpStatus.OK);
            }
            final List<Card> cards = cardRepository.findAll();
            final List<Card> filteredCards = new ArrayList<>();
            for (final Card card : cards){
                boolean cardRemove = false;
                for (int i = 0; i < dto.getToRemove().size(); i++) {
                    if(dto.getToRemove().get(i).compareTo(card.getId())==0)
                        cardRemove = true;
                }
                if(cardRemove)
                    continue;
                filteredCards.add(card);
            }
            Collections.shuffle(filteredCards);
            final List<CardResponse> cardResponses = new ArrayList<>();
        for (int i = 0; i < 4 - dto.getToRemove().size(); i++) {
            cardResponses.add(modelMapper.map(filteredCards.get(i),CardResponse.class));
        }
        final CardListResponseDto cardListResponseDto = new CardListResponseDto();
        cardListResponseDto.setCards(cardResponses);
        return new ResponseEntity(cardListResponseDto,HttpStatus.OK);
    }

    public ResponseEntity<CardListResponseDto> getFourRandomCards(){
        final List<Card> cardList = cardRepository.getRandomCards();
        final List<CardResponse> cardResponseList = new LinkedList<>();
        for (Card card : cardList) {
            cardResponseList.add(modelMapper.map(card, CardResponse.class));
        }
        return new ResponseEntity(cardResponseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CardResponse> createCard(final CardCreateDto cardCreateDto,final Principal principal) {
        final Optional<User> user = userRepository.findByUsername(principal.getName());

        Card card = modelMapper.map(cardCreateDto,Card.class);
        card = cardRepository.save(card);

        user.get().getCards().add(card);
        userRepository.save(user.get());
        return new ResponseEntity(
                modelMapper.map(card,CardResponse.class)
                ,HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<PointDto> incrementPoint(Principal principal) {
        final Optional<User> user = userRepository.findByUsername(principal.getName());
        if(Objects.isNull(user))
            throw new ServiceException(ErrorCodeEnum.ENTITY_FOUND,"User not found");
        user.get().setPoints(user.get().getPoints()+1);


        User savedUser = userRepository.save(user.get());
        final PointDto pointDto = modelMapper.map(savedUser,PointDto.class);

        return new ResponseEntity<>(pointDto,HttpStatus.OK);
    }
}
