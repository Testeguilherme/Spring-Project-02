package com.devsuperior.dslist.services;


import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //Serve para implementar a regra de negócio
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
        List<Game> result = gameRepository.findAll();
        //TRANSFORMAR UMA LISTA Game EM UMA LISTA GameMinDTO
        List<GameMinDTO> list = result.stream().map(x -> new GameMinDTO(x)).toList();
        return list;
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id){
        Game game = gameRepository.findById(id).get();
        GameDTO gameDto = new GameDTO(game);
        return gameDto;
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findGamesByListId(Long id){
        List<GameMinProjection> result = gameRepository.searchByList(id);
        List<GameMinDTO> list = result.stream().map(x -> new GameMinDTO(x)).toList();
        return list;
    }



}
