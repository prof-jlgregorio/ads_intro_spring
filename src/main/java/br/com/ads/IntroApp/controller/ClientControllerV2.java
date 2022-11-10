package br.com.ads.IntroApp.controller;

import br.com.ads.IntroApp.model.ClientModel;
import br.com.ads.IntroApp.service.ClientServiceV1;
import br.com.ads.IntroApp.service.ClientServiceV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients/v2")
@Api(value = "Client Endpoint Version V2")
public class ClientControllerV2 {

    @Autowired
    private ClientServiceV2 service;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE })
    @ApiOperation(value = "Returns a Client by ID")
    public ClientModel findById(
            @ApiParam(name = "id", value = "A valid integer value", required = true)
            @PathVariable("id") long id){
        //..........................................................
        var clientModel = service.findById(id);
        if(clientModel.isPresent()){
            buildEntityLink(clientModel.get());
            return clientModel.get();
        } else {
            return null;
        }
        //............................................................
    }

    @GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<PagedModel<ClientModel>> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            PagedResourcesAssembler<ClientModel> assembler
    ){
        var sortDirection = "desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "name"));

        Page<ClientModel> clients = service.findAll(pageable);

        for(ClientModel client : clients){
            buildEntityLink(client);
        }

        return new ResponseEntity(assembler.toModel(clients), HttpStatus.OK);

    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
    consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ClientModel save(@RequestBody ClientModel model){
        return service.save(model);
    }

    @PutMapping( produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE } )
    public ClientModel update(@RequestBody ClientModel model){
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id){
        Optional<ClientModel> found = service.findById(id);
        if(found.isPresent()){
          service.delete(id);
          return ResponseEntity.ok().build();
        } else {
            return null;
        }
    }

    @GetMapping("/find/email/{email}")
    public List<ClientModel> findByEmail(@PathVariable("email") String email){
        return service.findByEmail(email);
    }

    private void buildEntityLink(ClientModel model){
        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                this.getClass()).findById(model.getId())
                        ).withSelfRel()
        );

        if(!model.getProfession().hasLinks()) {
            Link professionLink = WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(
                            ProfessionController.class).findById(model.getProfession().getId())
                    ).withSelfRel();
            model.getProfession().add(professionLink);
        }
    }

}
