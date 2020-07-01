package canabarro.fraga.finalproject.controller;

import canabarro.fraga.finalproject.model.ClientEntity;
import canabarro.fraga.finalproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Luís Felipe
 * @author Matheus Canabarro
 * @version 1.0.1
 * @since 28/06/2020 - 19:55
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientFindAllService clientFindAllService;

    @Autowired
    private ClientFindByIdService clientFindByIdService;

    @Autowired
    private ClientSaveService clientSaveService;

    @Autowired
    private ClientUpdateService clientUpdateService;

    @Autowired
    private ClientDeleteService clientDeleteService;

    @GetMapping
    public ResponseEntity<List<ClientEntity>> findAll() {
        return new ResponseEntity<List<ClientEntity>>( this.clientFindAllService.findAll() , new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientEntity> findbyId(@PathVariable("id") long id) {
        ClientEntity clientEntity = this.clientFindByIdService.findById(id);
        if (clientEntity != null) {
            return new ResponseEntity<ClientEntity>(clientEntity, new HttpHeaders(), HttpStatus.OK);
        }
        return new ResponseEntity<ClientEntity>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ClientEntity> save(@Valid @RequestBody ClientEntity clientEntity) {
        return new ResponseEntity<ClientEntity>(this.clientSaveService.save(clientEntity),new HttpHeaders(),HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<ClientEntity> update(@RequestBody ClientEntity clientEntity) {
        return new ResponseEntity<ClientEntity>( this.clientUpdateService.update(clientEntity),new HttpHeaders(),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ClientEntity> delete(@PathVariable("id") long id) {
        this.clientDeleteService.delete(id);
        return new ResponseEntity<ClientEntity>(new HttpHeaders(),HttpStatus.OK);
    }
}
