package pt.egitron.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pt.egitron.demo.model.WebHook;

import java.util.List;

@NoRepositoryBean //Read-Only
public interface AbstractWebHookRepo<EntityType extends WebHook> extends CrudRepository<EntityType, Integer> {
    @Query("select e from #{#entityName} e")
        // #{#entityName} will be magically replaced by type arguments in children
    List<EntityType> findThemAll();
}
