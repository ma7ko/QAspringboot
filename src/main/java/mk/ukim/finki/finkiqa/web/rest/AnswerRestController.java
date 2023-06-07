package mk.ukim.finki.finkiqa.web.rest;

import mk.ukim.finki.finkiqa.model.Answer;
import mk.ukim.finki.finkiqa.model.Question;
import mk.ukim.finki.finkiqa.model.dto.AnswerDto;
import mk.ukim.finki.finkiqa.model.dto.QuestionDto;
import mk.ukim.finki.finkiqa.model.dto.mapper.AnswerMapper;
import mk.ukim.finki.finkiqa.service.AnswerService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/answers")
public class AnswerRestController {

    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    public AnswerRestController(AnswerService answerService,
    							AnswerMapper answerMapper) {
        this.answerService = answerService;
        this.answerMapper = answerMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> findById(@PathVariable Long id) {
        return this.answerService.getAnswerById(id)
                .map(answer -> ResponseEntity.ok().body(answer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Answer> save(@RequestBody AnswerDto answerDto) {
    	try {
    		Optional<Answer> answer = this.answerService.save(answerDto.getExplanation(), answerDto.getLikes(), answerDto.getDislikes(), answerDto.getQuestionId(), answerDto.getUsername());
    		if (answer.isEmpty()) {
    			return ResponseEntity.badRequest().build();
    		}
    		return ResponseEntity.ok(answer.get());
    	} catch (Exception exception) {
    		return ResponseEntity.badRequest().build();
    	}
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Answer> deleteAnswer(@PathVariable Long id) {
        this.answerService.deleteById(id);
        if(this.answerService.getAnswerById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<AnswerDto> edit(@PathVariable Long id, @RequestBody AnswerDto answerDto) {
    	Optional<Answer> optionalAnswer = this.answerService.edit(id, answerDto.getExplanation(), answerDto.getLikes(), answerDto.getDislikes(), answerDto.getQuestionId(), answerDto.getUsername());
    	if (optionalAnswer.isEmpty()) {
    		return ResponseEntity.notFound().build();
    	}
    	        
    	Answer answer = optionalAnswer.get();
    	AnswerDto answerDTO = this.answerMapper.toDTO(answer);
    	return ResponseEntity.ok().body(answerDTO);
    }

    @GetMapping("/{id}/like-by/{username}")
    public ResponseEntity<AnswerDto> likeAnswer(@PathVariable Long id, @PathVariable String username) {
    	Optional<Answer> optionalAnswer = this.answerService.likeAnswerById(id, username);
        
        if (optionalAnswer.isEmpty()) {
        	return ResponseEntity.notFound().build();
        }
        
        Answer answer = optionalAnswer.get();
        AnswerDto answerDTO = this.answerMapper.toDTO(answer);
        return ResponseEntity.ok().body(answerDTO);
    }

    @GetMapping("/{id}/dislike-by/{username}")
    public ResponseEntity<AnswerDto> dislikeAnswer(@PathVariable Long id, @PathVariable String username) {
    	Optional<Answer> optionalAnswer = this.answerService.dislikeAnswerById(id, username);
        
        if (optionalAnswer.isEmpty()) {
        	return ResponseEntity.notFound().build();
        }
        
        Answer answer = optionalAnswer.get();
        AnswerDto answerDTO = this.answerMapper.toDTO(answer);
        return ResponseEntity.ok().body(answerDTO);
    }
}
