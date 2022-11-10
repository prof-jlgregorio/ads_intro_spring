package br.com.ads.IntroApp.controller;

import br.com.ads.IntroApp.model.ClientModel;
import br.com.ads.IntroApp.service.ClientServiceV1;
import br.com.ads.IntroApp.service.ClientServiceV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<ClientModel> findById(
            @ApiParam(name = "id", value = "A valid integer value", required = true)
            @PathVariable("id") long id){
        return service.findById(id);
    }

    @GetMapping( produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<ClientModel> findAll(){
        return  service.findAll();
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

}
