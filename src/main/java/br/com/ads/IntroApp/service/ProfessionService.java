package br.com.ads.IntroApp.service;

import br.com.ads.IntroApp.model.ProfessionModel;
import br.com.ads.IntroApp.repository.ProfessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionService {

    @Autowired
    private ProfessionRepository repository;

    public List<ProfessionModel> findAll(){
        return repository.findAll();
    }

    public Optional<ProfessionModel> findById(long id){
        return repository.findById(id);
    }


}
