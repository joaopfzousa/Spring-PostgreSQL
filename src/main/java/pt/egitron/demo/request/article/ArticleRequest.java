package pt.egitron.demo.request.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.egitron.demo.model.ArticleDetails;
import pt.egitron.demo.model.Feature;
import pt.egitron.demo.model.Machine;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ArticleRequest {
    private String name;
    private String internalCode;
    private List<Feature> features;
    private List<Machine> machines;
    private ArticleDetails articleDetails;

    @Override
    public String toString() {
        return "ArticleCreateRequest {" +
                "name='" + name + '\'' +
                ", internalCode='" + internalCode + '\'' +
                '}';
    }
}
