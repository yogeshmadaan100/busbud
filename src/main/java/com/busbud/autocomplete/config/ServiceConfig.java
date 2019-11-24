package com.busbud.autocomplete.config;

import com.busbud.autocomplete.cache.CityCacheImpl;
import com.busbud.autocomplete.cache.ICache;
import com.busbud.autocomplete.dao.CityDaoImpl;
import com.busbud.autocomplete.dao.ICityDao;
import com.busbud.autocomplete.model.City;
import com.busbud.autocomplete.score.IScoreStrategy;
import com.busbud.autocomplete.score.LocationAlternateNamePopulationStrategy;
import com.busbud.autocomplete.service.AutoCompleteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.busbud.autocomplete")
public class ServiceConfig {

    @Bean
    ICityDao getCityDao() {
        return CityDaoImpl.getInstance();
    }

    @Bean
    IScoreStrategy getScoreStrategy() {
        return new LocationAlternateNamePopulationStrategy();
    }

    @Bean
    ICache getCityCache() {
        return new CityCacheImpl();
    }

    @Bean
    AutoCompleteService getAutoCompleteService(ICityDao cityDao, IScoreStrategy scoreStrategy, ICache cache) {
        return new AutoCompleteService(cityDao, scoreStrategy, cache);
    }



}
