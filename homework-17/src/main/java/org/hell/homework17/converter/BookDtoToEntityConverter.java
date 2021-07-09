package org.hell.homework17.converter;

import org.hell.homework17.dto.BookDto;
import org.hell.homework17.model.Book;
import org.modelmapper.ModelMapper;

public class BookDtoToEntityConverter {

    private final ModelMapper modelMapper;

    public BookDtoToEntityConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Book convert(BookDto dto) {
        return modelMapper.map(dto, Book.class);
    }
}
