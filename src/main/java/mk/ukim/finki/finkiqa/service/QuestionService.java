package mk.ukim.finki.finkiqa.service;

import mk.ukim.finki.finkiqa.model.Question;
import mk.ukim.finki.finkiqa.model.Tag;
import mk.ukim.finki.finkiqa.model.dto.search.PagedResponse;
import mk.ukim.finki.finkiqa.model.dto.search.SearchRequest;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
	PagedResponse<Question> listAll(SearchRequest request);
    Optional<Question> getQuestionById(Long Id);
    Optional<Question> save(String title, String description, Long likes, Long dislikes, String userId, List<String> tags);
    Optional<Question> edit(Long Id, String title, String description, Long likes, Long dislikes, String userId, List<String> tags);
    Optional<Question> likeQuestionById(Long id, String username);
    Optional<Question> dislikeQuestionById(Long id, String username);
    Optional<List<Tag>> searchTags(String pattern);
    void deleteById(Long Id);
}
