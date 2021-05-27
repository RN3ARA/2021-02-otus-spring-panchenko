package org.hell.homework10.converter;

import org.hell.homework10.dto.BookDto;
import org.hell.homework10.model.Book;
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
