package pt.egitron.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pt.egitron.demo.model.MicrosoftTeams;
import pt.egitron.demo.model.Slack;
import pt.egitron.demo.model.WebHook;
import pt.egitron.demo.request.webHook.WebHookRequest;
import pt.egitron.demo.service.WebHookService;

import java.util.List;

@RestController
public class WebHookController {

    private final WebHookService webHookService;

    @Autowired
    public WebHookController( WebHookService webHookService) {
        this.webHookService = webHookService;
    }

    @PostMapping("/webhooks")
    public WebHook createWebHook(@RequestBody WebHookRequest webHook){
        return webHookService.createWebHook(webHook);
    }

    @GetMapping("/webhooks")
    public void getAllWebHooks() {
        for (WebHook w : webHookService.getAllWebHooks()) {
            w.sendMessage();
        }
    }

    @GetMapping("/webhooks/{discriminator}")
    public List<WebHook> getWebHooksByDiscriminator(@PathVariable("discriminator") Integer discriminator) {
        return webHookService.getWebHooksByDiscriminator(discriminator);
    }

    @PutMapping("/webhooks/{id}")
    public WebHook editWebHooksById(@PathVariable("id") Integer id, @RequestBody WebHookRequest webHook) {
        return webHookService.editWebHooksById(id, webHook);
    }

    @DeleteMapping("/webhooks/{id}")
    public String deleteWebHooksById(@PathVariable("id") Integer id) {
        return webHookService.deleteWebHook(id);
    }
}