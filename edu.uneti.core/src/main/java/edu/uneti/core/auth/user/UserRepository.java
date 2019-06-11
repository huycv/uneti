package edu.uneti.core.auth.user;

import java.util.Optional;

import edu.uneti.core.shared.BaseRepository;

public interface UserRepository extends BaseRepository<User> {
	
	public Optional<User> findByIdNotAndUsername(String id, String username);
	
	public Optional<User> findByIdNotAndEmail(String id, String email);
	
	public Optional<User> findByUserNameOrEmail(String username, String email);
	
	public Optional<User> findByResetToken(String resetToken);

}
