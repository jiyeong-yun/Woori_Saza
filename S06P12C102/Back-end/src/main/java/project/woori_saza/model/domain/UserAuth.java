package project.woori_saza.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth {

    @Id
    @Column(name="auth_id")
    private String id;

    @Column(columnDefinition = "boolean default false")
    private Boolean isAdmin;


    @OneToOne(mappedBy = "userAuth", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private UserProfile userProfile;

}
