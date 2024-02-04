package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorMapper mapper;

    public List<AuthorDTO> getAll(){
        return repository.findAll().stream().map(mapper::map).toList();
    }

    public AuthorDTO getById(Long id){
        return mapper.map(getByIdEntity(id));
    }

    private Author getByIdEntity(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Author with id % not found", id)));
    }

    public AuthorDTO create(AuthorCreateDTO dto){
        Author author = mapper.map(dto);
        repository.save(author);
        return mapper.map(author);
    }

    public void delete(Long id){
        repository.delete(getByIdEntity(id));
    }

    public AuthorDTO update(Long id, AuthorUpdateDTO dto){
        Author author = getByIdEntity(id);
        mapper.update(dto, author);
        repository.save(author);
        return mapper.map(author);
    }

    // END
}
