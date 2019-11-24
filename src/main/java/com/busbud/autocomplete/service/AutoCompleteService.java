package com.busbud.autocomplete.service;

import com.busbud.autocomplete.dao.ICityDao;
import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.model.CityDTO;
import com.busbud.autocomplete.score.IScoreStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class AutoCompleteService {
    private ICityDao cityDao;
    private IScoreStrategy scoreStrategy;

    public AutoCompleteService(ICityDao cityDao, IScoreStrategy scoreStrategy) {
        this.cityDao = cityDao;
        this.scoreStrategy = scoreStrategy;
    }


    public List<CityDTO> getSuggestions(String query, double latitude, double longitude) {
        Iterator<City> searchResult = cityDao.searchByPrefix(query).iterator();
        PriorityQueue<CityDTO> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if(o1.getScore() > o2.getScore())
                return -1;
            if(o1.getScore() < o2.getScore())
                return 1;
            return 0;
        });
        while (searchResult.hasNext()) {
            City city = searchResult.next();
            priorityQueue.offer(city.ToDTO(scoreStrategy.getScore(query, latitude, longitude, city)));
        }
        return new ArrayList<>(priorityQueue);
    }
}
