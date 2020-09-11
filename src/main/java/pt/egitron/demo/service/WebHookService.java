package pt.egitron.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import pt.egitron.demo.model.Machine;
import pt.egitron.demo.model.MicrosoftTeams;
import pt.egitron.demo.model.Slack;
import pt.egitron.demo.model.WebHook;
import pt.egitron.demo.repository.WebHookRepo;
import pt.egitron.demo.request.webHook.WebHookRequest;
import pt.egitron.hypno.exception.PreConditionFailedException;

import java.util.List;
import java.util.Optional;

@Service
public class WebHookService {

    private final WebHookRepo webHookRepo;

    @Autowired
    public WebHookService(WebHookRepo webHookRepo) {
        this.webHookRepo = webHookRepo;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public WebHook createWebHook(WebHookRequest webHookRequest){

        WebHook webHook = null;
        switch (webHookRequest.getApp())
        {
            case 1:
                webHook = new Slack(webHookRequest.getUrl(), webHookRequest.getChannel());
                break;
            case 2:
                webHook = new MicrosoftTeams(webHookRequest.getUrl(), webHookRequest.getChannel());
                break;
            default:
                throw new PreConditionFailedException("Mete ai uma app!!");
        }
        webHookRepo.save(webHook);

        return webHook;
    }

    public List<WebHook> getAllWebHooks() {
       return webHookRepo.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<WebHook> getWebHooksByDiscriminator(Integer discriminator) {
        List<WebHook> webHooks = null;
        switch (discriminator)
        {
            case 1:
                webHooks = webHookRepo.getWebHooksFromSlack();
                break;
            case 2:
                webHooks = webHookRepo.getWebHooksFromMicrosoftTeams();
                break;
            default:
                throw new PreConditionFailedException("Mete ai um discriminator!!");
        }

        return webHooks;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public WebHook editWebHooksById(Integer id, WebHookRequest webHook) {
        WebHook w = this.findById(id);

        w.setUrl(webHook.getUrl());
        w.setChannel(webHook.getChannel());

        return w;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteWebHook(Integer id)
    {
        webHookRepo.deleteById(id);

        return "Remove article with id = " + id;
    }

    public WebHook findById(Integer id)
    {
        Optional<WebHook> webHookOpt = webHookRepo.findById(id);

        WebHook w;
        if(webHookOpt.isPresent()) w = webHookOpt.get();
        else return null;

        return w;
    }
}
