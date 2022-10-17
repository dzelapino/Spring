package pl.dzelapino.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
interface SqlGameRepository extends GameRepository, JpaRepository<Game, String> {
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from games where id=:id")
    boolean existsById(@Param("id") String id);

    @Override
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from games where id=:id")
    int delete(@Param("id") String id);
}
