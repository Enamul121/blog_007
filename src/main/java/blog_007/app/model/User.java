package blog_007.app.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 
 @Column(name = "email")
 private String email;
 
 @Column(name = "firstname")
 private String firstname; 
 
 @Column(name = "lastname")
 private String lastname;
 
 @Column(name = "password")
 private String password;

 @Transient
 private String confirmPassword;
 
 @Column(name = "active")
 private int active;
 
 
 @ManyToMany(cascade=CascadeType.ALL)
 @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), 
            inverseJoinColumns=@JoinColumn(name="role_id"))
 private Set<Role> roles;

/*

 @OneToMany(mappedBy = "user")
 private List<Post> posts;

*/



 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getFirstname() {
  return firstname;
 }

 public void setFirstname(String firstname) {
  this.firstname = firstname;
 }

 public String getLastname() {
  return lastname;
 }

 public void setLastname(String lastname) {
  this.lastname = lastname;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getConfirmPassword() {
  return confirmPassword;
 }

 public void setConfirmPassword(String confirmPassword) {
  this.confirmPassword = confirmPassword;
 }

 public int getActive() {
  return active;
 }

 public void setActive(int active) {
  this.active = active;
 }

 public Set<Role> getRoles() {
  return roles;
 }

 public void setRoles(Set<Role> roles) {
  this.roles = roles;
 }


/* public List<Post> getPosts() {
  return posts;
 }

 public void setPosts(List<Post> posts) {
  this.posts = posts;
 }
 */

}