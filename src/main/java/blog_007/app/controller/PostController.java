package blog_007.app.controller;


import blog_007.app.model.Post;
import blog_007.app.model.User;
import blog_007.app.service.IPostService;
import blog_007.app.service.UserService;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);


    @Autowired
    private IPostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/createPost")
    public ModelAndView postPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("post", new Post());
        model.setViewName("user/postForm");
        return model;

    }

    @PostMapping("/user/createPost")
    public ModelAndView createPost(@Valid Post post, BindingResult result, Principal principal) {

        ModelAndView model = new ModelAndView();

        Optional<User> user = Optional.ofNullable(userService.findUserByEmail(principal.getName()));
        if (result.hasErrors()) {
            return new ModelAndView("user/postForm");

        } else {
            if (user.isPresent()) {
                post.setUser(user.get());
                postService.savePost(post);
                model.addObject("msg", "Post has been Created successfully!");
                return new ModelAndView("user/home");
            } else {
                return new ModelAndView("user/postForm");
            }
        }
    }


    // Single Post
    @GetMapping("/post/{id}")
    public ModelAndView singlePost(@PathVariable Long id, Principal principal) {
        ModelAndView model = new ModelAndView();

        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addObject("post", post);

            if (isOwnerOfThePost(principal,post)){
                model.addObject("userEmail", principal.getName());
            }
        }
        model.setViewName("singlePost");
        return model;
    }


      // Edit Post
    @GetMapping("/post/edit/{id}")
    public ModelAndView editPost(@PathVariable("id") Long id, Principal principal){
        ModelAndView model = new ModelAndView();

        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isPresent()){
            Post post = optionalPost.get();

            if (isOwnerOfThePost(principal,post)){
                model.addObject("post" , post);
                model.setViewName("user/postForm");
            }

        }
        return model;
    }



      // Delete Post
      @GetMapping("/post/delete/{id}")
      public ModelAndView deletePost(@PathVariable("id") Long id, Principal principal){
          ModelAndView model = new ModelAndView("");

          Optional<Post> optionalPost = postService.findById(id);
          if (optionalPost.isPresent()){
              Post post = optionalPost.get();

              if (isOwnerOfThePost(principal,post)){
                  postService.delete(post);
                  model.setViewName("redirect:/allArticles");
              }
          }

          return model;

      }

    private boolean isOwnerOfThePost(Principal principal, Post post){
        return principal != null && principal.getName().equals(post.getUser().getEmail());
    }


}
