package com.cardapp.card.game.repository;

import com.cardapp.card.entity.Card;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,String > {
    @Query(nativeQuery = true,value = "SELECT * FROM card ORDER BY random() LIMIT 4")
    public List<Card> getRandomCards();
}
