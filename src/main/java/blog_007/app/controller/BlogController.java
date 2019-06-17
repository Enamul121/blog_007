package blog_007.app.controller;


import blog_007.app.model.Post;
import blog_007.app.model.User;
import blog_007.app.repo.PostRepo;
import blog_007.app.service.IPostService;
import blog_007.app.service.UserService;
import blog_007.app.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@Controller
public class BlogController {

    @Autowired
    private IPostService postService;
    @Autowired
    private UserService userService;


    @GetMapping("/user/{username}")
    public ModelAndView usersBlog(@PathVariable String username, @RequestParam(defaultValue = "0") int page) {

        ModelAndView model = new ModelAndView();
        Optional<User> optionalUser = Optional.ofNullable(userService.findUserByEmail(username));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Page<Post> posts = postService.findByUserOrderedByDatePageable(user, page);
            Pager pager = new Pager(posts);
            model.addObject("pager", pager);
            model.addObject("user", user);
            model.setViewName("user/blog");
            return model;
        } else {
            return null;
        }
    }


    @Autowired
    private PostRepo postRepo;


  @GetMapping("/allArticles")
    public ModelAndView allArticles(){
       ModelAndView model = new ModelAndView();
        List<Post> postList = postRepo.getAllByIdAndOrderByIdBodyDesc();
        model.addObject("postList", postList);
        model.setViewName("allPost");
        return model;

    }

/*
    @GetMapping("/allArticles")
    public ModelAndView allPost(){
        ModelAndView model = new ModelAndView("allPost");
        List<Post> posts = postRepo.findAll(Sort.by(Sort.Direction.DESC));

        model.addObject("postList", posts);
        return model;
    }
*/



}
