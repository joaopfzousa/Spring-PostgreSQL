package pt.egitron.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.egitron.demo.model.WebHook;

import java.util.List;

@Repository
public interface WebHookRepo extends AbstractWebHookRepo<WebHook>, JpaRepository<WebHook, Integer> {

    @Query(value = "select s from Slack s")
    List<WebHook> getWebHooksFromSlack();

    @Query(value = "select mst from MicrosoftTeams mst")
    List<WebHook> getWebHooksFromMicrosoftTeams();
}
