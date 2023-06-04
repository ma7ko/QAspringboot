package mk.ukim.finki.finkiqa.web.rest;

import mk.ukim.finki.finkiqa.model.Answer;
import mk.ukim.finki.finkiqa.model.Question;
import mk.ukim.finki.finkiqa.model.Tag;
import mk.ukim.finki.finkiqa.model.dto.QuestionDto;
import mk.ukim.finki.finkiqa.model.dto.mapper.QuestionMapper;
import mk.ukim.finki.finkiqa.model.dto.search.PagedResponse;
import mk.ukim.finki.finkiqa.model.dto.search.SearchRequest;
import mk.ukim.finki.finkiqa.service.AnswerService;
import mk.ukim.finki.finkiqa.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/questions")
public class QuestionRestController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final QuestionMapper questionMapper;

    public QuestionRestController(QuestionService questionService,
                                  AnswerService answerService,
                                  QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.questionMapper = questionMapper;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<QuestionDto>> listAllQuestions(@RequestParam Integer page, @RequestParam Integer size) {
    	try {
    		PagedResponse<Question> questions = this.questionService.listAll(new SearchRequest(page, size));
    		PagedResponse<QuestionDto> questionDtos = new PagedResponse<QuestionDto>();
    		questionDtos.setContent(this.questionMapper.toDTOs(questions.getContent()));
    		questionDtos.setCount(questions.getCount());
    		questionDtos.setTotalCount(questions.getTotalCount());
    		
    		return ResponseEntity.ok(questionDtos);
    	} catch (Exception exception) {
    		return ResponseEntity.badRequest().build();
    	}
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> findById(@PathVariable Long id) {
    	
        Optional<Question> optionalQuestion = this.questionService.getQuestionById(id);
        
        if (optionalQuestion.isEmpty()) {
        	return ResponseEntity.notFound().build();
        }
        
        Question question = optionalQuestion.get();
        QuestionDto questionDTO = this.questionMapper.toDTO(question);
        
        return ResponseEntity.ok().body(questionDTO);
        
        //	PREVIOUS IMPLEMENTATION
        		//	return
        		//this.questionService.getQuestionById(id)
                //.map(question -> ResponseEntity.ok().body(question))
                //.orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/answers")
    public List<Answer> listAllAnswersByQuestionId(@PathVariable Long id) {
        return this.answerService.getAnswersFromQuestionId(id);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Long id) {
        this.questionService.deleteById(id);
        if(this.questionService.getQuestionById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/add")
    public ResponseEntity<Question> save(@RequestBody QuestionDto questionDto) {
        return this.questionService.save(questionDto.getTitle(), questionDto.getDescription(), questionDto.getLikes(), questionDto.getDislikes(), questionDto.getUsername(), questionDto.getTags())
                .map(question -> ResponseEntity.ok().body(question))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Question> edit(@PathVariable Long id, @RequestBody QuestionDto questionDto) {
        return this.questionService.edit(id, questionDto.getTitle(), questionDto.getDescription(), questionDto.getLikes(), questionDto.getDislikes(), questionDto.getUsername(), questionDto.getTags())
                .map(question -> ResponseEntity.ok().body(question))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}/like-by/{userId}")
    public ResponseEntity<Question> likeQuestion(@PathVariable Long id, @PathVariable String userId) {
        return this.questionService.likeQuestionById(id, userId)
                .map(question -> ResponseEntity.ok().body(question))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/dislike-by/{userId}")
    public ResponseEntity<Question> dislikeQuestion(@PathVariable Long id, @PathVariable String userId) {
        return this.questionService.dislikeQuestionById(id, userId)
                .map(question -> ResponseEntity.ok().body(question))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/find-tags-containing/{pattern}")
    public ResponseEntity<List<Tag>> findTags(@PathVariable String pattern) {
        return this.questionService.searchTags(pattern)
                .map(tags -> ResponseEntity.ok().body(tags))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
