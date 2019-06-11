package edu.uneti.core.auth.user;

import java.io.UnsupportedEncodingException;

import edu.uneti.core.auth.role.Roles;
import edu.uneti.core.shared.BusinessException;

public interface UserService {

	public User create(String username, String email, String password, String fullname, Roles role) throws BusinessException;
	
	public User updateLoginInfo(User userInfo) throws BusinessException;
	
	public void delete(String id) throws BusinessException;
	
	public String getDisplayName(String usernameoremail);
	
	public void setLastActiveTime(String usernameoremail);
	
	public void forgetPassword(String username) throws BusinessException, UnsupportedEncodingException;
	
	public void changeRole(String email, Roles newRole) throws BusinessException;
	
	public boolean isExisted(String username, String email);
	
	public void changePassword(String resetToken, String password) throws BusinessException;
	
}
