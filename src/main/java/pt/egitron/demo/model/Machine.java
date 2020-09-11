package pt.egitron.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@SequenceGenerator(name = "machine_gen", sequenceName = "machine_seq", allocationSize = 16)
@Where(clause = "status <> 3")
@EqualsAndHashCode(of = { "internalCode", "name" }, callSuper = false)
public class Machine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "machine_gen")
    private Integer id;

    @Column(name = "machine_name", length = 127)
    private String name;

    @Column(name = "machine_code", length = 127)
    private String internalCode;

    @JsonIgnore
    @ManyToMany(mappedBy = "machines")
    private Set<Article> articles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "machine")
    private List<ArticleMachineInfo> articleMachineInfo = new ArrayList<>();
}
