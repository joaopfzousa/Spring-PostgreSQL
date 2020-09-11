package pt.egitron.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@SequenceGenerator(name = "article_gen", sequenceName = "article_seq", allocationSize = 16)
@Where(clause = "status <> 3")
@EqualsAndHashCode(of = { "internalCode", "name" }, callSuper = false)
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_gen")
    private Integer id;

    @Column(name = "article_name", length = 127)
    private String name;

    @Column(name = "article_code", length = 127)
    private String internalCode;

    //@JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feature> features = new ArrayList<>();


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "article_machine",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "machine_id"))
    private Set<Machine> machines = new HashSet<>();

    @OneToOne(mappedBy = "article", cascade = CascadeType.ALL)
    private ArticleDetails articleDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleMachineInfo> articleMachineInfo = new ArrayList<>();

    @Autowired
    public Article(String name, String internalCode) {
        this.name = name;
        this.internalCode = internalCode;
    }

    public void addMachine(Machine machine) {
        this.machines.add(machine);
        machine.getArticles().add(this);
    }

    public void removeMachine(Machine machine) {
        this.machines.remove(machine);
        machine.getArticles().remove(this);
    }

    public void addFeature(Feature f){
        this.features.add(f);
        f.setArticle(this);
    }

    public void remFeature(Feature f){
        this.features.remove(f);
    }

    public void setArticleDetails(ArticleDetails articleDetails){
        if(articleDetails == null) {
            if (this.articleDetails != null){
                this.articleDetails.setArticle(null);
            }
        }else{
            articleDetails.setArticle(this);
        }
        this.articleDetails = articleDetails;
    }

    public void addArticleMachineInfo(Machine m, String tag) {
        this.articleMachineInfo.add(new ArticleMachineInfo(this, m, tag));
    }

    public void removeArticleMachineInfo(Machine m) {
        Iterator<ArticleMachineInfo> articleMachineInfoIterator = this.articleMachineInfo.iterator();
        ArticleMachineInfo tmpArticleMachineInfo;

        while (articleMachineInfoIterator.hasNext())
        {
            tmpArticleMachineInfo = articleMachineInfoIterator.next();

            if(m.getId().equals(tmpArticleMachineInfo.getMachine().getId()) && this.id.equals(tmpArticleMachineInfo.getArticle().getId())){
                articleMachineInfoIterator.remove();
                tmpArticleMachineInfo.getMachine().getArticleMachineInfo().remove(tmpArticleMachineInfo);
                tmpArticleMachineInfo.setMachine(null);
                tmpArticleMachineInfo.setArticle(null);
                tmpArticleMachineInfo.setTag(null);
            }
        }


    }
}
