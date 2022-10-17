package pl.dzelapino.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PlatformRepository {

    List<Platform> findAll();
    Page<Platform> findAll(Pageable page);
    Optional<Platform> findById(String id);
    boolean existsById(String id);
    Platform save(Platform entity);
    int delete(String id);

}
