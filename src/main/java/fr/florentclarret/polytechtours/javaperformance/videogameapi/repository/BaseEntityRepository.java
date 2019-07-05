package fr.florentclarret.polytechtours.javaperformance.videogameapi.repository;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

    Optional<T> findByName(final String name);

}
