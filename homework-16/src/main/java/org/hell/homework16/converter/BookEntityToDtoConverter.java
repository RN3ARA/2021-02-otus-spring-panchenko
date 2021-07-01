package org.hell.homework16.converter;

import org.hell.homework16.dto.BookDto;
import org.hell.homework16.model.Book;
import org.modelmapper.ModelMapper;

public class BookEntityToDtoConverter {

    private final ModelMapper modelMapper;

    public BookEntityToDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public BookDto convert(Book book) {
        return modelMapper.map(book, BookDto.class);
    }
}
