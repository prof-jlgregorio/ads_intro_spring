package br.com.ads.IntroApp.controller;


import br.com.ads.IntroApp.model.ProfessionModel;
import br.com.ads.IntroApp.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profession/v1")
public class ProfessionController {

    @Autowired
    private ProfessionService service;

    @GetMapping()
    public List<ProfessionModel> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ProfessionModel findById(@PathVariable("id") long id){
        var professionModel = service.findById(id);
        if(professionModel.isPresent()){
            return professionModel.get();
        } else{
            return null;
        }
    }

}
