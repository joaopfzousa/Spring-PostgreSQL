package pt.egitron.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pt.egitron.demo.interfaces.CommunicationMessages;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discriminator",
        discriminatorType = DiscriminatorType.INTEGER)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "web_hook_gen", sequenceName = "web_hook_seq", allocationSize = 8)
@Table(name = "web_hook", uniqueConstraints = {@UniqueConstraint(columnNames = {"url"})})
public abstract class WebHook extends BaseEntity implements CommunicationMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "web_hook_gen")
    private Integer id;

    @Column(nullable = false, name = "url", length = 1024)
    private String url;

    @Column(nullable = false)
    private String channel;

    public WebHook(String url, String channel) {
        this.url = url;
        this.channel = channel;
    }
}
