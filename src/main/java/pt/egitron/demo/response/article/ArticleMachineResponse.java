package pt.egitron.demo.response.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ArticleMachineResponse {

    private Integer id;

    private String name;

    private String internalCode;

    private List<String> machines = new ArrayList<>();

    public ArticleMachineResponse(Integer id, String name, String internalCode) {
        this.id = id;
        this.name = name;
        this.internalCode = internalCode;
    }
}
