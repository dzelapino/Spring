package pl.dzelapino.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GameRepository {

    List<Game> findAll();
    Page<Game> findAll(Pageable page);
    Optional<Game> findById(String id);
    boolean existsById(String id);
    Game save(Game entity);
    int delete(String id);

}
