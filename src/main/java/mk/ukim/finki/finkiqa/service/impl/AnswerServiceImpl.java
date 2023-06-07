package mk.ukim.finki.finkiqa.service.impl;

import mk.ukim.finki.finkiqa.model.Answer;
import mk.ukim.finki.finkiqa.model.Question;
import mk.ukim.finki.finkiqa.model.User;
import mk.ukim.finki.finkiqa.model.dto.search.PagedResponse;
import mk.ukim.finki.finkiqa.model.dto.search.SearchRequest;
import mk.ukim.finki.finkiqa.model.dto.search.util.SearchRequestUtilClass;
import mk.ukim.finki.finkiqa.repository.AnswerRepository;
import mk.ukim.finki.finkiqa.repository.QuestionRepository;
import mk.ukim.finki.finkiqa.repository.UserRepository;
import mk.ukim.finki.finkiqa.service.AnswerService;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository,
                             QuestionRepository questionRepository,
                             UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Answer> listAll() {
        return this.answerRepository.findAll();
    }

    @Override
    public Optional<Answer> getAnswerById(Long Id) {
        return this.answerRepository.findById(Id);
    }

    @Override
    public PagedResponse<Answer> getAnswersFromQuestionId(Long id, SearchRequest searchRequest) {
    	
    	final Page<Answer> answers = this.answerRepository.findAllByQuestionId(id, SearchRequestUtilClass.toPageRequest(searchRequest, "posted"));
        
        if (answers.isEmpty()) {
        	return new PagedResponse<Answer>(Collections.emptyList(), 0L, answers.getTotalElements());
        }
        
        return new PagedResponse<Answer>(answers.getContent(), (long) answers.getSize(), answers.getTotalElements());
        
    	// 		PREVIOUS IMPLEMENTATION
    	//Comparator<Answer> dateComparator = (c1, c2) -> {
        //    if (c1.getPosted().isBefore(c2.getPosted())) return -1;
        //    else return 1;
        //};

        //return this.answerRepository.findAllByQuestionId(id).stream().sorted(dateComparator.reversed()).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<Answer> save(String explanation, Long likes, Long dislikes, Long questionId, String userId) {
    	
    	if (questionId == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	if (userId == null || userId.isEmpty()) {
    		throw new IllegalArgumentException();
    	}
    	
        Question question = this.questionRepository.findById(questionId).orElseThrow(IllegalAccessError::new);
        User user = this.userRepository.findByUsername(userId).orElseThrow(IllegalAccessError::new);

        return Optional.of(this.answerRepository.save(new Answer(explanation, likes, dislikes, question, user)));
    }

    @Override
    public Optional<Answer> edit(Long Id, String explanation, Long likes, Long dislikes, Long questionId, String userId) {
        Answer answer = this.answerRepository.findById(Id).orElseThrow(IllegalAccessError::new);

        answer.setExplanation(explanation);
        answer.setLikes(likes);
        answer.setDislikes(dislikes);

        Question question = this.questionRepository.findById(questionId).orElseThrow(IllegalAccessError::new);
        answer.setQuestion(question);
        User user = this.userRepository.findByUsername(userId).orElseThrow(IllegalAccessError::new);
        answer.setUser(user);

        return Optional.of(this.answerRepository.save(answer));

    }

    @Override
    public Optional<Answer> likeAnswerById(Long id, String username) {
        Answer answer = this.answerRepository.findById(id).orElseThrow(IllegalAccessError::new);
        User user = this.userRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
        List<User> usersLiked = answer.getLikedByUsers();
        Long currentLikes = answer.getLikes();

        if (!usersLiked.contains(user)) {
            currentLikes += 1;
            usersLiked.add(user);
            answer.setLikedByUsers(usersLiked);
        } else {
            currentLikes -= 1;
            usersLiked.remove(user);
            answer.setLikedByUsers(usersLiked);
        }

        answer.setLikes(currentLikes);
        return Optional.of(this.answerRepository.save(answer));
    }

    @Override
    public Optional<Answer> dislikeAnswerById(Long id, String username) {
        Answer answer = this.answerRepository.findById(id).orElseThrow(IllegalAccessError::new);
        User user = this.userRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
        List<User> usersDisliked = answer.getDislikedByUsers();
        Long currentDislikes = answer.getDislikes();

        if (!usersDisliked.contains(user)) {
            currentDislikes += 1;
            usersDisliked.add(user);
            answer.setDislikedByUsers(usersDisliked);
        } else {
            currentDislikes -= 1;
            usersDisliked.remove(user);
            answer.setDislikedByUsers(usersDisliked);
        }

        answer.setDislikes(currentDislikes);
        return Optional.of(this.answerRepository.save(answer));
    }

    @Override
    public void deleteById(Long Id) {
        this.answerRepository.deleteById(Id);
    }
}
