package mk.ukim.finki.finkiqa.repository;

import mk.ukim.finki.finkiqa.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByNameContains(String pattern);
    List<Tag> findByNameIn(List<String> names);
    Optional<Tag> findByName(String name);
}
