package pl.dzelapino.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "games")
public
class Game {
    @Id
    private String id;
    private String name;
    @Embedded
    private final Audit audit = new Audit();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "playable_on",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id"))
    @JsonIgnoreProperties("games")
    private Set<Platform> platforms;

    Game() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<Platform> platforms) {
        this.platforms = platforms;
    }

    public void updateFrom(final Game source) {
        name = source.name;
    }
}
