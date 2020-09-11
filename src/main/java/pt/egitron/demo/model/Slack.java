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
@DiscriminatorValue("1")
public class Slack extends WebHook {

    public Slack(String url, String channel) {
        super(url, channel);
    }

    @Override
    public void sendMessage() {
        System.out.printf("Olá estás no Slack!!\n" );
    }
}
