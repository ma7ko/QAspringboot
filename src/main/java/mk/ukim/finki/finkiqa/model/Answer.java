package mk.ukim.finki.finkiqa.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    private String explanation;

    private Long likes;

    private Long dislikes;

    private LocalDateTime posted;

    private LocalDateTime lastEdited;

    @ManyToOne
    private Question question;

    @ManyToMany
    private List<User> likedByUsers;

    @ManyToMany
    private List<User> dislikedByUsers;

    @OneToOne
    private User user;

    public Answer() {
    }

    public Answer(String explanation, Long likes, Long dislikes, Question question, User user) {
        this.explanation = explanation;
        this.likes = likes;
        this.dislikes = dislikes;
        this.question = question;
        this.user = user;
        this.posted = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Long getLikes() {
		return likes;
	}

	public void setLikes(Long likes) {
		this.likes = likes;
	}

	public Long getDislikes() {
		return dislikes;
	}

	public void setDislikes(Long dislikes) {
		this.dislikes = dislikes;
	}

	public LocalDateTime getPosted() {
		return posted;
	}

	public void setPosted(LocalDateTime posted) {
		this.posted = posted;
	}

	public LocalDateTime getLastEdited() {
		return lastEdited;
	}

	public void setLastEdited(LocalDateTime lastEdited) {
		this.lastEdited = lastEdited;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<User> getLikedByUsers() {
		return likedByUsers;
	}

	public void setLikedByUsers(List<User> likedByUsers) {
		this.likedByUsers = likedByUsers;
	}

	public List<User> getDislikedByUsers() {
		return dislikedByUsers;
	}

	public void setDislikedByUsers(List<User> dislikedByUsers) {
		this.dislikedByUsers = dislikedByUsers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
