package com.nh7.ecommerce.config;

import com.nh7.ecommerce.util.ModelMapperUtil;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapperUtil modelMapper() {
        ModelMapperUtil modelMapper = new ModelMapperUtil();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
