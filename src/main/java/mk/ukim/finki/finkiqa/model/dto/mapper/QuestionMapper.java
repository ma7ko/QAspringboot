package mk.ukim.finki.finkiqa.model.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import mk.ukim.finki.finkiqa.model.Question;
import mk.ukim.finki.finkiqa.model.dto.QuestionDto;

@Component
public class QuestionMapper {

	public QuestionDto toDTO(Question question) {
	
		QuestionDto dto = new QuestionDto();
		
		dto.setId(question.getId());
		dto.setTitle(question.getTitle());
		dto.setDescription(question.getDescription());
		dto.setLikes(question.getLikes());
		dto.setDislikes(question.getDislikes());
		dto.setUsername(question.getUser().getUsername());
		dto.setTags(question.getTags().stream().map(t -> t.getName()).collect(Collectors.toList()));
		dto.setLikedBy(question.getLikedByUsers().stream().map(l -> l.getUsername()).collect(Collectors.toList()));
		dto.setDislikedBy(question.getDislikedByUsers().stream().map(d -> d.getUsername()).collect(Collectors.toList()));
		dto.setDatePosted(question.getPosted().toString());
		dto.setDateLastEdited(question.getLastEdited().toString());
		
		return dto;
	}
	
	public List<QuestionDto> toDTOs(List<Question> questions) {
		
		List<QuestionDto> dtos = new ArrayList<>();
		
		for(Question question : questions) {
			QuestionDto dto = this.toDTO(question);
			dtos.add(dto);
		}
		
		return dtos;
	}
}
