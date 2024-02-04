package exercise.service;

import exercise.dto.*;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository repository;

    @Autowired
    private BookMapper mapper;

    public List<BookDTO> getAll(){
        return repository.findAll().stream().map(mapper::map).toList();
    }

    public BookDTO getById(Long id){
        return mapper.map(getByIdEntity(id));
    }

    private Book getByIdEntity(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id % not found", id)));
    }

    public BookDTO create(BookCreateDTO dto){
        Book book = mapper.map(dto);
        repository.save(book);
        return mapper.map(book);
    }

    public void delete(Long id){
        repository.delete(getByIdEntity(id));
    }

    public BookDTO update(Long id, BookUpdateDTO dto){
        Book book = getByIdEntity(id);
        mapper.update(dto, book);
        repository.save(book);
        return mapper.map(book);
    }
    // END
}
