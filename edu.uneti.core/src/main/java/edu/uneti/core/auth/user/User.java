package edu.uneti.core.auth.user;

import java.time.LocalDateTime;
import java.util.UUID;

import edu.uneti.core.auth.role.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	/** The id */
	private String id;
	
	/** The username */
	private String username;
	
	private String email;
	
	/** The password */
	private String password;
	
	/** The role */
	private Roles role;
	
	/** The fullname */
	private String fullname;
	
	/** The reset token */
	private LocalDateTime createDate;
	
	/** The reset token */
	private LocalDateTime lastActive;
	
	/** The reset token */
	private String resetToken;
	
	/** The reset token valid */
	private LocalDateTime resetTokenValid;
	
	/** The state */
	private UserStatus status;
	
	public boolean isActive() {
		return this.status == UserStatus.ACTIVE;
	}
	
	public boolean isInactive() {
		return this.status == UserStatus.INACTIVE;
	}
	
	public static User createUserActive(String username, String email, String password, Roles role, String fullname) {
		return User.builder()
				.id(UUID.randomUUID().toString())
				.username(username)
				.email(email)
				.password(password)
				.role(role)
				.fullname(fullname)
				.status(UserStatus.ACTIVE)
				.createDate(LocalDateTime.now())
				.build();
	}
	
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public User active() {
		this.status = UserStatus.ACTIVE;
		this.lastActive = LocalDateTime.now();
		return this;
	}
	
	public User forgetPassword() {
		this.resetToken = UUID.randomUUID().toString();
		this.resetTokenValid = LocalDateTime.now().plusMinutes(120);
		return this;
	}
	
	public User changePassword(String newPassword) {
		this.password = newPassword;
		this.resetToken = null;
		this.resetTokenValid = null;
		return this;
	}
	
	public User changeRole(Roles role) {
		this.role = role;
		return this;
	}
}
