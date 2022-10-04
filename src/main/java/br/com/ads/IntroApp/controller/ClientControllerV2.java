package br.com.ads.IntroApp.controller;

import br.com.ads.IntroApp.model.ClientModel;
import br.com.ads.IntroApp.service.ClientServiceV1;
import br.com.ads.IntroApp.service.ClientServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients/v2")
public class ClientControllerV2 {

    @Autowired
    private ClientServiceV2 service;

    @GetMapping("/{id}")
    public Optional<ClientModel> findById(@PathVariable("id") long id){
        return service.findById(id);
    }

    @GetMapping
    public List<ClientModel> findAll(){
        return  service.findAll();
    }

    @PostMapping
    public ClientModel save(@RequestBody ClientModel model){
        return service.save(model);
    }

    @PutMapping
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
