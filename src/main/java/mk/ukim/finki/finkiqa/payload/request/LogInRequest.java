package mk.ukim.finki.finkiqa.payload.request;

import lombok.Data;

@Data
public class LogInRequest {
    private String username;
    private String password;

    public LogInRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
