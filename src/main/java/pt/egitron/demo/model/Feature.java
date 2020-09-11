package pt.egitron.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@SequenceGenerator(name = "feature_gen", sequenceName = "feature_seq", allocationSize = 16)
@Where(clause = "status <> 3")
public class Feature extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feature_gen")
    private Integer id;

    @Column(name = "feature_name", length = 127)
    private String name;

    @Column(name = "feature_code", length = 127)
    private String internalCode;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;
}
