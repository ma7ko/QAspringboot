package mk.ukim.finki.finkiqa.model.dto;

import lombok.Data;

@Data
public class TagDto {

    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
