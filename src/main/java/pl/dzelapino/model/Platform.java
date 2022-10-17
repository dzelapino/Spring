package pl.dzelapino.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "platforms")
public
class Platform {

    @Id
    private String id;
    @NotBlank(message = "Platform's name can not be empty")
    private String name;
    @Embedded
    private final Audit audit = new Audit();
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "platforms")
    @JsonIgnoreProperties("platforms")
    private Set<Game> games;

    Platform() {

    }

    public String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setCreators(Set<Game> games) {
        this.games = games;
    }

    public void updateFrom(final Platform source) {
        name = source.name;
    }

}
