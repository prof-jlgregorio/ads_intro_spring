package br.com.ads.IntroApp.service;

import br.com.ads.IntroApp.model.ClientModel;
import br.com.ads.IntroApp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceV2 {

    @Autowired
    private ClientRepository repository;

    public Optional<ClientModel> findById(long id){
        return repository.findById(id);
    }

    public Page<ClientModel> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public ClientModel save(ClientModel model){
        return repository.save(model);
    }

    public ClientModel update(ClientModel model){
        var found = repository.findById(model.getId());
        if(found.isPresent()){
            found.get().setName(model.getName());
            found.get().setGender(model.getGender());
            found.get().setCity(model.getCity());
            //..new attribute of V2 - email
            found.get().setEmail(model.getEmail());
            return repository.save(found.get());
        } else {
            return null;
        }
    }

    public void delete(long id){
        var found = repository.findById(id);
        if(found.isPresent()){
            repository.delete(found.get());
        }
    }

    public List<ClientModel> findByEmail(String email){
        return repository.findByEmailStartsWithIgnoreCase(email);
    }


}
