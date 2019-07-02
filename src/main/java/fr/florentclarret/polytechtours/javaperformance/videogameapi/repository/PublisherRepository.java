package fr.florentclarret.polytechtours.javaperformance.videogameapi.repository;

import fr.florentclarret.polytechtours.javaperformance.videogameapi.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
