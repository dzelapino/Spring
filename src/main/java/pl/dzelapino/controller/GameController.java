package pl.dzelapino.controller;

import pl.dzelapino.model.Game;
import pl.dzelapino.model.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);
    private final GameRepository repository;

    public GameController(GameRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/games", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Game>> readAllGames() {
        logger.warn("Exposing games.");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/games")
    ResponseEntity<List<Game>> readGamesPage(Pageable page) {
        logger.warn("Exposing games page.");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping("/games/{id}")
    ResponseEntity<Optional<Game>> getGame(@PathVariable String id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findById(id));
    }

    @PostMapping("/games")
    ResponseEntity<?> createGame(@RequestBody @Valid Game toCreate) {
        if(repository.existsById(toCreate.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Game result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping("/games/{id}")
    ResponseEntity<?> updateGame(@PathVariable String id, @RequestBody Game toUpdate) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id).ifPresent(project -> {
            project.updateFrom(toUpdate);
            repository.save(project);
        });
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/games/{id}")
    ResponseEntity<?> deleteGame(@PathVariable String id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.delete(id));
    }

}
