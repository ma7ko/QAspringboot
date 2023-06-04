package mk.ukim.finki.finkiqa.payload.request;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class SignUpRequest {
    private String username;
    private String email;
    private String password;

    public SignUpRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Set<String> getRole() {
        Set<String> roles = new HashSet<>();
        String role = email.split("@")[0];
        switch (role) {
            case "admin" :
                roles.add("admin");
            case "mod" :
                roles.add("mod");
            case "user":
                roles.add("user");
                break;
            default:
                roles = null;
        }
        return roles;
    }

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
