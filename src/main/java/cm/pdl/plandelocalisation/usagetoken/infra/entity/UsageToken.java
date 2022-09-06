package cm.pdl.plandelocalisation.usagetoken.infra.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Georges DEFO
 * @date 23/08/2022
 */
@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class UsageToken {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String value;

    @Column(columnDefinition = "boolean default false")
    private boolean activated = true;

    public UsageToken(String value) {
        this.value = value;
    }
}
