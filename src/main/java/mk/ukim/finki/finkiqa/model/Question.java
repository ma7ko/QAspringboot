package mk.ukim.finki.finkiqa.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private Long likes;

    private Long dislikes;

    private LocalDateTime posted;

    private LocalDateTime lastEdited;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Tag> tags;

    @ManyToMany
    private List<User> likedByUsers;

    @ManyToMany
    private List<User> dislikedByUsers;

    public Question() {
    }

    public Question(String title, String description, Long likes, Long dislikes, User user, List<Tag> tags) {
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.dislikes = dislikes;
        this.user = user;
        this.tags = tags;
        this.likedByUsers = new ArrayList<>();
        this.posted = LocalDateTime.now();
        this.lastEdited = LocalDateTime.now();
    }

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
}
