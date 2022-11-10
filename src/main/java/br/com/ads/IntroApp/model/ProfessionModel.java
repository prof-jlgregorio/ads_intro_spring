package br.com.ads.IntroApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import springfox.documentation.service.Representation;

import javax.persistence.*;

@Entity
@Table(name = "professions")
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionModel extends RepresentationModel {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Setter @Getter
    private long id;

    @Column(length = 50, nullable = false)
    @Setter @Getter
    private String name;

}
