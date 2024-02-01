package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO dto){
        Contact contact = toEntity(dto);
        contact = contactRepository.save(contact);
        return toDTO(contact);
    }

    private Contact toEntity(ContactCreateDTO dto){
        Contact contact = new Contact();

        contact.setFirstName(dto.getFirstName());
        contact.setPhone(dto.getPhone());
        contact.setLastName(dto.getLastName());

        return contact;
    }

    private ContactDTO toDTO(Contact contact){
        ContactDTO dto = new ContactDTO();

        dto.setFirstName(contact.getFirstName());
        dto.setPhone(contact.getPhone());
        dto.setLastName(contact.getLastName());
        dto.setId(contact.getId());
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setUpdatedAt(contact.getUpdatedAt());

        return dto;
    }
    // END
}
