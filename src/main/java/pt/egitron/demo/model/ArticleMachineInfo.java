package pt.egitron.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pt.egitron.demo.keys.ArticleMachineInfoKey;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode( callSuper = false)
public class ArticleMachineInfo {

    @EmbeddedId
    private ArticleMachineInfoKey id;

    @Column(length = 127)
    private String tag;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("articleId")
    @JoinColumn(name = "article_id")
    private Article article;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("machineId")
    @JoinColumn(name = "machine_id")
    private Machine machine;

    public ArticleMachineInfo(Article article, Machine machine, String tag) {
        this.article = article;
        this.machine = machine;
        this.tag = tag;
        this.id = new ArticleMachineInfoKey(this.article.getId(), this.machine.getId());
    }
}
