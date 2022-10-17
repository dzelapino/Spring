package pl.dzelapino.controller;

import pl.dzelapino.model.Platform;
import pl.dzelapino.model.PlatformRepository;
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
public class PlatformController {

    private static final Logger logger = LoggerFactory.getLogger(PlatformController.class);
    private final PlatformRepository repository;

    public PlatformController(PlatformRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/platforms", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Platform>> readAllPlatforms() {
        logger.warn("Exposing all platforms.");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/platforms")
    ResponseEntity<List<Platform>> readAllPlatforms(Pageable page) {
        logger.warn("Exposing platforms page.");
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping("/platforms/{id}")
    ResponseEntity<Optional<Platform>> getPlatform(@PathVariable String id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findById(id));
    }

    @PostMapping("/platforms")
    ResponseEntity<?> createPlatform(@RequestBody @Valid Platform toCreate) {
        if(repository.existsById(toCreate.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Platform result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping("/platforms/{id}")
    ResponseEntity<?> updatePlatform(@PathVariable String id, @RequestBody Platform toUpdate) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.findById(id).ifPresent(platform -> {
            platform.updateFrom(toUpdate);
            repository.save(platform);
        });
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/platforms/{id}")
    ResponseEntity<?> deletePlatform(@PathVariable String id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.delete(id));
    }

}
