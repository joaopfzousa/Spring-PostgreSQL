package pt.egitron.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.egitron.demo.model.Article;
import pt.egitron.demo.model.ArticleMachineInfo;
import pt.egitron.demo.request.article.ArticleRequest;
import pt.egitron.demo.request.articleMachineInfo.ArticleMachineInfoRequest;
import pt.egitron.demo.response.articleMachineInfo.ArticleMachineInfoResponse;
import pt.egitron.demo.service.ArticleMachineInfoService;

import java.util.List;

@RestController
public class ArticleMachineInfoController {

    private final ArticleMachineInfoService articleMachineInfoService;

    @Autowired
    public ArticleMachineInfoController(ArticleMachineInfoService articleMachineInfoService) {
        this.articleMachineInfoService = articleMachineInfoService;
    }

    @GetMapping("/articles/machines/info")
    public List<ArticleMachineInfoResponse> getAllArticles() {
        return articleMachineInfoService.getAllArticlesMachinesInfo();
    }

    @PostMapping("/articles/machines")
    public ArticleMachineInfo createArticleMachineInfo(@RequestBody ArticleMachineInfoRequest articleMachineInfoRequest) {
        return articleMachineInfoService.createArticleMachineInfo(articleMachineInfoRequest);
    }

    @PostMapping("/articles/machines/cascade")
    public void createArticleMachineInfoCascade(@RequestBody ArticleMachineInfoRequest articleMachineInfoRequest) {
        articleMachineInfoService.createArticleMachineInfoCascade(articleMachineInfoRequest);
    }

    @DeleteMapping("/articles/machines/cascade")
    public void deleteArticleMachineInfoCascade(@RequestBody ArticleMachineInfoRequest articleMachineInfoRequest) {
        articleMachineInfoService.deleteArticleMachineInfoCascade(articleMachineInfoRequest);
    }

    @DeleteMapping("/articles/machines")
    public void deleteArticleMachineInfo(@RequestBody ArticleMachineInfoRequest articleMachineInfoRequest) {
        articleMachineInfoService.deleteArticleMachineInfo(articleMachineInfoRequest);
    }

    @PutMapping("/articles/machines")
    public void editArticleMachineInfo( @RequestBody ArticleMachineInfoRequest articleMachineInfoRequest){
        articleMachineInfoService.editArticleMachineInfo(articleMachineInfoRequest);
    }
}
