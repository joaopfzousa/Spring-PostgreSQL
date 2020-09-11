package pt.egitron.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@DiscriminatorValue("2")
public class MicrosoftTeams extends WebHook {

    public MicrosoftTeams(String url, String channel) {
        super(url, channel);
    }

    @Override
    public void sendMessage() {
        System.out.printf("Olá estás no MSTeams!!\n");
    }
}
