package pt.egitron.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode( callSuper = false)
public class ArticleDetails implements Serializable {

    private static final long serialVersionUID = 7672557825725750249L;

    @Id
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @OneToOne
    @MapsId
    private Article article;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private Integer peso;

    @Column(length = 100)
    private Integer tamanho;



}
