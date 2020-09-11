package pt.egitron.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import pt.egitron.demo.keys.ArticleMachineInfoKey;
import pt.egitron.demo.model.Article;
import pt.egitron.demo.model.ArticleMachineInfo;
import pt.egitron.demo.model.Machine;
import pt.egitron.demo.repository.ArticleMachineInfoRepo;
import pt.egitron.demo.repository.ArticleRepo;
import pt.egitron.demo.repository.MachineRepo;
import pt.egitron.demo.request.articleMachineInfo.ArticleMachineInfoRequest;
import pt.egitron.demo.response.articleMachineInfo.ArticleMachineInfoResponse;

import java.util.List;

@Service
public class ArticleMachineInfoService {

    private final ArticleMachineInfoRepo articleMachineInfoRepo;

    private final ArticleRepo articleRepo;

    private final MachineRepo machineRepo;

    @Autowired
    public ArticleMachineInfoService(ArticleMachineInfoRepo articleMachineInfoRepo, ArticleRepo articleRepo, MachineRepo machineRepo) {
        this.articleMachineInfoRepo = articleMachineInfoRepo;
        this.articleRepo = articleRepo;
        this.machineRepo = machineRepo;
    }

    public List<ArticleMachineInfoResponse> getAllArticlesMachinesInfo()
    {
        return articleMachineInfoRepo.getAllArticleMachineInfo();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ArticleMachineInfo createArticleMachineInfo(ArticleMachineInfoRequest articleMachineInfoRequest){

        Article a = articleRepo.getOne(articleMachineInfoRequest.getArticle_id());

        Machine m = machineRepo.getOne(articleMachineInfoRequest.getMachine_id());

        ArticleMachineInfo articleMachineInfo = new ArticleMachineInfo(a, m, articleMachineInfoRequest.getTag());

        articleMachineInfoRepo.save(articleMachineInfo);

        return articleMachineInfo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void createArticleMachineInfoCascade(ArticleMachineInfoRequest articleMachineInfoRequest){
        Article a = articleRepo.getOne(articleMachineInfoRequest.getArticle_id());

        Machine m = machineRepo.getOne(articleMachineInfoRequest.getMachine_id());

        a.addArticleMachineInfo(m, articleMachineInfoRequest.getTag());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteArticleMachineInfoCascade(ArticleMachineInfoRequest articleMachineInfoRequest){
        Article a = articleRepo.getOne(articleMachineInfoRequest.getArticle_id());

        Machine m = machineRepo.getOne(articleMachineInfoRequest.getMachine_id());

        a.removeArticleMachineInfo(m);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteArticleMachineInfo(ArticleMachineInfoRequest articleMachineInfoRequest){
        articleMachineInfoRepo.deleteById(new ArticleMachineInfoKey(articleMachineInfoRequest.getArticle_id(), articleMachineInfoRequest.getMachine_id()));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editArticleMachineInfo(ArticleMachineInfoRequest articleMachineInfoRequest){
        articleMachineInfoRepo.editArticleMachineInfo(articleMachineInfoRequest.getMachine_id(), articleMachineInfoRequest.getArticle_id(), articleMachineInfoRequest.getTag());
    }
}
