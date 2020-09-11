package pt.egitron.demo.request.webHook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WebHookRequest {

    private String url;

    private String channel;

    private Integer app;
}
