package br.com.ads.IntroApp.repository;

import br.com.ads.IntroApp.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    Optional<ClientModel> findById(long id);

    List<ClientModel> findByNameContainsIgnoreCaseOrderByName(String name);

}
