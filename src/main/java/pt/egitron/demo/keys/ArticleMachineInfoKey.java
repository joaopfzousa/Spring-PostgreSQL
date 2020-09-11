package pt.egitron.demo.keys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class ArticleMachineInfoKey implements Serializable {

    private static final long serialVersionUID = 3202221465187546940L;

    private Integer articleId;

    private Integer machineId;

}
