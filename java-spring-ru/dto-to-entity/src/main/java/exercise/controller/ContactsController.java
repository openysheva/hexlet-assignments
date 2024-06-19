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
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contact) {
        var contactEntity = toEntity(contact); // Сначала в Entity
        contactRepository.save(contactEntity);
        var contactEntityDTO = toDTO(contactEntity); // Потом в DTO
        return contactEntityDTO;
    }

    private ContactDTO toDTO(Contact contact) {
        var dto = new ContactDTO();
        dto.setLastName(contact.getLastName());
        dto.setFirstName(contact.getFirstName());
        dto.setPhone(contact.getPhone());
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setUpdatedAt(contact.getUpdatedAt());
        dto.setId(contact.getId());

        return dto;
    }

    private Contact toEntity(ContactCreateDTO contactDto) {
        var contact = new Contact();
        contact.setLastName(contactDto.getLastName());
        contact.setFirstName(contactDto.getFirstName());
        contact.setPhone(contactDto.getPhone());

        return contact;
    }
    // END
}
