package blog_007.app.service;

import blog_007.app.model.User;

public interface UserService {


	 User findUserByEmail(String email);

	 User findById(Long id);
	
	  void saveUser(User user);
}
