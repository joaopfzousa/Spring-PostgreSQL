package pt.egitron.demo.request.articleMachineInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ArticleMachineInfoRequest {

    private Integer article_id;

    private Integer machine_id;

    private String tag;
}
