package org.hell.homework10.converter;

import org.hell.homework10.dto.BookDto;
import org.hell.homework10.model.Book;
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
