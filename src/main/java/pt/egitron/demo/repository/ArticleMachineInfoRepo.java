package pt.egitron.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pt.egitron.demo.keys.ArticleMachineInfoKey;
import pt.egitron.demo.model.ArticleMachineInfo;
import pt.egitron.demo.response.articleMachineInfo.ArticleMachineInfoResponse;

import java.util.List;

public interface ArticleMachineInfoRepo extends JpaRepository<ArticleMachineInfo, ArticleMachineInfoKey> {

    @Query(value = "select new pt.egitron.demo.response.articleMachineInfo.ArticleMachineInfoResponse(concat(m.name, ' - ', a.name), am.tag) from ArticleMachineInfo am " +
            "join am.machine m " +
            "join am.article a ")
    List<ArticleMachineInfoResponse> getAllArticleMachineInfo();

    @Modifying
    @Query(value = "update ArticleMachineInfo am set am.tag = :tag where am.machine.id = :machineId and am.article.id = :articleId")
    void editArticleMachineInfo(Integer machineId, Integer articleId, String tag);
}
