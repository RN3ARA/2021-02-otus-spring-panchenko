package org.hell.homework17.config;

import org.hell.homework17.converter.BookDtoToEntityConverter;
import org.hell.homework17.converter.BookEntityToDtoConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BookEntityToDtoConverter bookEntityDtoConverter(ModelMapper modelMapper) {
        return new BookEntityToDtoConverter(modelMapper);
    }

    @Bean
    public BookDtoToEntityConverter bookDtoToEntityConverter(ModelMapper modelMapper) {
        return new BookDtoToEntityConverter(modelMapper);
    }
}
