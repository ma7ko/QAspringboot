package mk.ukim.finki.finkiqa.repository;

import mk.ukim.finki.finkiqa.model.Answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestionId(Long id);
    Page<Answer> findAllByQuestionId(Long id, Pageable pageable);
}
