package blog_007.app.service;

import blog_007.app.model.Role;
import blog_007.app.model.User;
import blog_007.app.repo.RoleRespository;
import blog_007.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserServiceImpl implements UserService {

 @Qualifier("userRepository")
 @Autowired
 private UserRepository userRepository;
 
 @Autowired
 private RoleRespository roleRespository;

 
 @Autowired
 private BCryptPasswordEncoder bCryptPasswordEncoder;

 @Override
 public User findUserByEmail(String email) {
  return userRepository.findByEmail(email);
 }


 @Override
 public User findById(Long id) {
  return userRepository.findById(id).get();
 }

 @Override
 public void saveUser(User user) {
  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
  user.setActive(1);
  Role userRole = roleRespository.findByRole("USER");
  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
  userRepository.save(user);
 }

}
