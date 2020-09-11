package pt.egitron.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.egitron.demo.model.Article;
import pt.egitron.demo.request.article.ArticleRequest;
import pt.egitron.demo.response.article.ArticleMachineResponse;
import pt.egitron.demo.service.ArticleService;
import pt.egitron.hypno.exception.NotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/articles/{id}")
    public Article getArticleById(@PathVariable("id") Integer id) {
        Optional<Article> article = articleService.getArticleById(id);
        if(article.isPresent()) return article.get();
        else throw new NotFoundException("Doesn't exists Article with id = " + id);
    }

    @PostMapping("/articles")
    public Article createArticle(@RequestBody ArticleRequest article){
        return articleService.createArticle(article);
    }

    @PutMapping("/articles/{id}")
    public Article editArticle(@PathVariable("id") Integer id, @RequestBody ArticleRequest article){
        return articleService.editArticle(id, article);
    }

    @DeleteMapping("/articles/{id}")
    public String deleteArticle(@PathVariable("id") Integer id){
        return articleService.deleteArticle(id);
    }

    @GetMapping("/articles/machines")
    public Collection<ArticleMachineResponse> getMachinesByArticleId(){
        return articleService.getMachinesByArticleId();
    }
}
