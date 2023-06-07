package mk.ukim.finki.finkiqa.service;

import mk.ukim.finki.finkiqa.model.Answer;
import mk.ukim.finki.finkiqa.model.dto.search.PagedResponse;
import mk.ukim.finki.finkiqa.model.dto.search.SearchRequest;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    List<Answer> listAll();
    PagedResponse<Answer> getAnswersFromQuestionId(Long id, SearchRequest searchRequest);
    Optional<Answer> getAnswerById(Long Id);
    Optional<Answer> save(String explanation, Long likes, Long dislikes, Long questionId, String userId);
    Optional<Answer> edit(Long Id, String explanation, Long likes, Long dislikes, Long questionId, String userId);
    Optional<Answer> likeAnswerById(Long id, String username);
    Optional<Answer> dislikeAnswerById(Long id, String username);
    void deleteById(Long Id);
}
