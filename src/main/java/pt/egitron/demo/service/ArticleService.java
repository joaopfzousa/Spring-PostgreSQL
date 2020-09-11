package pt.egitron.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pt.egitron.demo.model.Article;
import pt.egitron.demo.model.ArticleDetails;
import pt.egitron.demo.model.Feature;
import pt.egitron.demo.model.Machine;
import pt.egitron.demo.repository.ArticleRepo;
import pt.egitron.demo.request.article.ArticleRequest;
import pt.egitron.demo.response.article.ArticleMachineResponse;

import javax.persistence.Tuple;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepo articleRepo;

    @Autowired
    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Article> getAllArticles()
    {
        return articleRepo.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<Article> getArticleById(Integer id)
    {
        return articleRepo.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Article createArticle(ArticleRequest article)
    {
        Article a = new Article(article.getName(), article.getInternalCode());

        if(article.getFeatures() != null)
        {
            for (Feature f: article.getFeatures()) {
                a.addFeature(f);
            }
        }

        if(article.getMachines() != null)
        {
            for (Machine m: article.getMachines()) {
                a.addMachine(m);
            }
        }
        if(article.getArticleDetails() != null){
            ArticleDetails articleDetails = article.getArticleDetails();
            a.setArticleDetails(articleDetails);
        }

        articleRepo.save(a);

        return a;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Article editArticle(Integer id, ArticleRequest article)
    {
        Optional<Article> article_opt = articleRepo.findById(id);

        Article a;
        if(article_opt.isPresent()) a = article_opt.get();
        else return null;

        Iterator<Feature> featureIterator = article.getFeatures().iterator();
        Feature tmp_feature ;
        List<Feature> newFeatures = new ArrayList<>();
        while (featureIterator.hasNext())
        {
            tmp_feature = featureIterator.next();
            if(tmp_feature.getId() == null)
            {
                newFeatures.add(tmp_feature);
                featureIterator.remove();
            }
        }

        Iterator<Machine> machineIterator = article.getMachines().iterator();
        Machine tmpMachine;
        List<Machine> newMachines = new ArrayList<>();

        while (machineIterator.hasNext())
        {
            tmpMachine = machineIterator.next();
            if(tmpMachine.getId() == null)
            {
                newMachines.add(tmpMachine);
                machineIterator.remove();
            }
        }


        /*
        //é igual ao que está feito em baixo
        for (Feature f: article.getFeatures()) {
            features.put(f.getId(), f);
        }
        */

        Map<Integer, Feature> features_recv = article.getFeatures().stream().filter(feature -> feature.getId() != null).collect(Collectors.toMap(feature -> feature.getId(), feature -> feature));

        Map<Integer, Feature> features_db = articleRepo.getFeaturesByArticleId(id).stream().collect(Collectors.toMap(feature -> feature.getId(), feature -> feature));

        HashSet<Integer> identifiers = new HashSet<>(features_recv.keySet());

        identifiers.addAll(features_db.keySet());

        Set<Integer> addFeatureSet = new HashSet<>(identifiers);
        addFeatureSet.removeAll(features_db.keySet());

        Set<Integer> remFeatureSet = new HashSet<>(identifiers);
        remFeatureSet.removeAll(features_recv.keySet());

        Set<Integer> editFeatureSet = new HashSet<>(identifiers);
        editFeatureSet.removeAll(remFeatureSet);
        editFeatureSet.removeAll(addFeatureSet);

        if(!addFeatureSet.isEmpty()){
            for (Integer f: addFeatureSet) {
                a.addFeature(articleRepo.getFeaturesById(f));
            }
        }

        if(!remFeatureSet.isEmpty()){
            for (Integer f: remFeatureSet) {
                a.remFeature(articleRepo.getFeaturesById(f));
            }
        }

        if(!editFeatureSet.isEmpty()){
            for (Integer f: editFeatureSet) {
                Feature feature = articleRepo.getFeaturesById(f);
                feature.setName(features_recv.get(f).getName());
                feature.setInternalCode(features_recv.get(f).getInternalCode());
            }
        }

        if(!newFeatures.isEmpty()) {
            for (Feature f: newFeatures) {
                a.addFeature(f);
            }
        }

        if(!newMachines.isEmpty()) {
            for (Machine m: newMachines) {
                a.addMachine(m);
            }
        }

        if(article.getArticleDetails() != null){
            ArticleDetails articleDetails = article.getArticleDetails();
            a.getArticleDetails().setName(articleDetails.getName());
            a.getArticleDetails().setPeso(articleDetails.getPeso());
            a.getArticleDetails().setTamanho(articleDetails.getTamanho());
        }


        return a;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteArticle(Integer id)
    {
        articleRepo.deleteById(id);

        return "Remove article with id = " + id;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public Collection<ArticleMachineResponse> getMachinesByArticleId()
    {
        List<Tuple> results =  articleRepo.getMachinesByArticleId();
        if(results.isEmpty()) return Collections.emptyList();

        Map<Integer, ArticleMachineResponse> articleMachineResponseMap = new HashMap<>();
        ArticleMachineResponse tmpArticleMachine;

        for (Tuple row: results) {
            tmpArticleMachine = articleMachineResponseMap.get((Integer) row.get("articleId"));

            if (tmpArticleMachine == null)
            {
                tmpArticleMachine = new ArticleMachineResponse((Integer)row.get("articleId"), (String) row.get("articleName"), (String) row.get("articleCode"));
                articleMachineResponseMap.put((Integer) row.get("articleId"), tmpArticleMachine );
            }

            tmpArticleMachine.getMachines().add((String) row.get("machineName"));
         }

        return articleMachineResponseMap.values();
    }
}