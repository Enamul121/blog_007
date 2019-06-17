package blog_007.app.controller;

import blog_007.app.model.Comment;
import blog_007.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class CommentsController {

/*

    @Autowired
    private CommentService commentService;


    @GetMapping("/post/createComment")
    public ModelAndView commentPage(){
        ModelAndView model = new ModelAndView();
        model.addObject("comment", new Comment());
        model.setViewName("singlePost");
        return model;
    }

    @PostMapping("/post/createComment")
    public ModelAndView comment(@Valid Comment comment, BindingResult result){
        ModelAndView model = new ModelAndView();

        if (result.hasErrors()){
            model.addObject("msg","Something going wrong seriously..........");
            return new ModelAndView("singlePost");
        }
        commentService.saveComment(comment);
        return model;
    }
*/



}
