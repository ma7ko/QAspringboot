package mk.ukim.finki.finkiqa.model.dto;

import lombok.Data;

import java.util.List;


@Data
public class QuestionDto {
	private Long id;
    private String title;
    private String description;
    private Long likes;
    private Long dislikes;
    private String username;
    private List<String> tags;
    private List<String> likedBy;
    private List<String> dislikedBy;
    private String datePosted;
    private String dateLastEdited;
    
    public QuestionDto() {
		super();
	}

	public QuestionDto(String title,
                       String description,
                       Long likes,
                       Long dislikes,
                       String username,
                       List<String> tags,
                       List<String> likedBy,
                       List<String> dislikedBy) {
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.dislikes = dislikes;
        this.username = username;
        this.tags = tags;
        this.likedBy = likedBy;
        this.dislikedBy = dislikedBy;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDatePosted() {
		return datePosted;
	}

	public String getDateLastEdited() {
		return dateLastEdited;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}

	public void setDateLastEdited(String dateLastEdited) {
		this.dateLastEdited = dateLastEdited;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Long getLikes() {
		return likes;
	}

	public Long getDislikes() {
		return dislikes;
	}

	public String getUsername() {
		return username;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public void setDislikes(Long dislikes) {
		this.dislikes = dislikes;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getLikedBy() {
		return likedBy;
	}

	public List<String> getDislikedBy() {
		return dislikedBy;
	}

	public void setLikedBy(List<String> likedBy) {
		this.likedBy = likedBy;
	}

	public void setDislikedBy(List<String> dislikedBy) {
		this.dislikedBy = dislikedBy;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
