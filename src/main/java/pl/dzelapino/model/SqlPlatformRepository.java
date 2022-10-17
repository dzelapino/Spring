package pl.dzelapino.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
interface SqlPlatformRepository extends PlatformRepository, JpaRepository<Platform, String> {
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from platforms where id=:id")
    boolean existsById(@Param("id") String id);

    @Override
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "delete from platforms where id=:id")
    int delete(@Param("id") String id);

}
