package mk.ukim.finki.finkiqa.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class AnswerDto {
	private Long id;
    private String explanation;
    private Long likes;
    private Long dislikes;
    private String datePosted;
    private String dateLastEdited;
    private Long questionId;
    private String username;
    private List<String> likedBy;
    private List<String> dislikedBy;
    

    
    public AnswerDto() {}

	public AnswerDto(String explanation, Long likes, Long dislikes, Long questionId, String username) {
        this.explanation = explanation;
        this.likes = likes;
        this.dislikes = dislikes;
        this.questionId = questionId;
        this.username = username;
    }
    
	public String getExplanation() {
		return explanation;
	}

	public Long getLikes() {
		return likes;
	}

	public Long getDislikes() {
		return dislikes;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public String getUsername() {
		return username;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public void setDislikes(Long dislikes) {
		this.dislikes = dislikes;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public String getDatePosted() {
		return datePosted;
	}

	public String getDateLastEdited() {
		return dateLastEdited;
	}

	public List<String> getLikedBy() {
		return likedBy;
	}

	public List<String> getDislikedBy() {
		return dislikedBy;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}

	public void setDateLastEdited(String dateLastEdited) {
		this.dateLastEdited = dateLastEdited;
	}

	public void setLikedBy(List<String> likedBy) {
		this.likedBy = likedBy;
	}

	public void setDislikedBy(List<String> dislikedBy) {
		this.dislikedBy = dislikedBy;
	}

}
