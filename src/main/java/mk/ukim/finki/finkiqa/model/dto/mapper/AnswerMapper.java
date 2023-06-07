package mk.ukim.finki.finkiqa.model.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import mk.ukim.finki.finkiqa.model.Answer;
import mk.ukim.finki.finkiqa.model.Question;
import mk.ukim.finki.finkiqa.model.dto.AnswerDto;
import mk.ukim.finki.finkiqa.model.dto.QuestionDto;

@Component
public class AnswerMapper {

	public AnswerDto toDTO(Answer answer) {
		
		AnswerDto dto = new AnswerDto();
		
		dto.setId(answer.getId());
		dto.setExplanation(answer.getExplanation());
		dto.setLikes(answer.getLikes());
		dto.setDislikes(answer.getDislikes());
		dto.setUsername(answer.getUser().getUsername());
		dto.setQuestionId(answer.getQuestion().getId());
		dto.setLikedBy(answer.getLikedByUsers().stream().map(l -> l.getUsername()).collect(Collectors.toList()));
		dto.setDislikedBy(answer.getDislikedByUsers().stream().map(d -> d.getUsername()).collect(Collectors.toList()));
		dto.setDatePosted(answer.getPosted().toString());
		dto.setDateLastEdited(answer.getLastEdited().toString());
		
		return dto;
	}
	
	public List<AnswerDto> toDTOs(List<Answer> answers) {
		
		List<AnswerDto> dtos = new ArrayList<>();
		
		for(Answer answer : answers) {
			AnswerDto dto = this.toDTO(answer);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
}
