package pt.egitron.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.egitron.demo.model.Article;
import pt.egitron.demo.model.Feature;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {

    @Query(value = "select f from Feature f where f.article.id = :id")
    List<Feature> getFeaturesByArticleId(Integer id);

    @Query(value = "select f from Feature f where f.id = :id")
    Feature getFeaturesById(Integer id);

    @Query(value = "select a.id as articleId, a.name as articleName, a.internalCode as articleCode, concat(machine.internalCode, ' - ',  machine.name) as machineName " +
            "from Article a " +
            "left join a.machines as machine ")
    List<Tuple> getMachinesByArticleId();

}
