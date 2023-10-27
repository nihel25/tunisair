package tn.esprit.tunisair.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
  @DiscriminatorColumn(name="dtype" , length=25)
//@Builder
@Table(name = "_user")
@SuperBuilder
    public   class User implements UserDetails {
 @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullname;
    private String telephone;
    private String prenom;
 private String adresse;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(unique = true)
    private String email;
    private String cin;
    @Enumerated(EnumType.STRING)
    private UserRole role;




    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Demandeclient> demandeclients;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Avis> avis;
@JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Session> sessions;


    @OneToMany(mappedBy = "user")
    private Set<Demandeformation> demandeformations;


    @OneToMany(mappedBy = "user")
    private List<Reclamation> reclamations;

@JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Stage> stage;


    @OneToMany(mappedBy ="user")
    private Set<Formation> formation;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
//
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
//}
}



//