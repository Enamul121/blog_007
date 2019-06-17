package blog_007.app.repo;

import blog_007.app.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("commentsRepo")
public interface CommentsRepo extends JpaRepository<Comment,Long> {


 /*  @Query(value = "select c.comments_id, c.comment_body, c.comments_date, c.post__id, c.user__id" +
           " from comment c inner join post p on (c.post__id=p.post_id) order by c.comments_id desc ")
   List<Comment> findAllCommentsOrOrderByDesc();*/



   }
