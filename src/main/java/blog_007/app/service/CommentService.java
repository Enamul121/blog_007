package blog_007.app.service;

import blog_007.app.model.Comment;
import blog_007.app.repo.CommentsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("iCommentService")
public class CommentService implements ICommentService {

    @Qualifier("commentsRepo")
    @Autowired
    private CommentsRepo commentsRepo;


    @Override
    public Comment saveComment(Comment comment) {
        return commentsRepo.save(comment);
    }
}
