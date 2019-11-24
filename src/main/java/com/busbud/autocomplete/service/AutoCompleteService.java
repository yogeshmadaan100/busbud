package com.busbud.autocomplete.service;

import com.busbud.autocomplete.cache.ICache;
import com.busbud.autocomplete.dao.ICityDao;
import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.model.CityDTO;
import com.busbud.autocomplete.score.IScoreStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AutoCompleteService {
    private ICityDao cityDao;
    private IScoreStrategy scoreStrategy;
    private ICache cityCache;

    public AutoCompleteService(ICityDao cityDao, IScoreStrategy scoreStrategy, ICache cityCache) {
        this.cityDao = cityDao;
        this.scoreStrategy = scoreStrategy;
        this.cityCache = cityCache;
    }

    public ResponseEntity<List<CityDTO>> getSuggestions(String query, Double latitude, Double longitude) {
        if (query == null || query.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if ((latitude == null && longitude != null) || (latitude != null && longitude == null))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (cityCache.isCachePresent(query, latitude, longitude)) {
            return ResponseEntity.ok(cityCache.getCache(query, latitude, longitude));
        }
        Iterator<City> searchResult = cityDao.searchByPrefix(query);
        if (latitude == null || latitude == 0.0) {
            List<CityDTO> result = new ArrayList<>();
            while (searchResult.hasNext()) {
                City city = searchResult.next();
                result.add(city.ToDTO(1));
            }
            cityCache.saveInCache(query, latitude, longitude, result);
            return ResponseEntity.ok(result);
        }

        Queue<CityDTO> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.getScore() > o2.getScore())
                return -1;
            if (o1.getScore() < o2.getScore())
                return 1;
            return 0;
        });

        while (searchResult.hasNext()) {
            City city = searchResult.next();
            priorityQueue.offer(city.ToDTO(scoreStrategy.getScore(query, latitude, longitude, city)));
        }
        List<CityDTO> result = new ArrayList<>(priorityQueue.size());
        while (!priorityQueue.isEmpty()) {
            result.add(priorityQueue.poll());
        }
        cityCache.saveInCache(query, latitude, longitude, result);
        return ResponseEntity.ok(result);
    }
}
