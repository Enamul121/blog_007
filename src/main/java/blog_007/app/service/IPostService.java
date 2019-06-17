package blog_007.app.service;

import blog_007.app.model.Post;
import blog_007.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPostService {

    Optional<Post> findById(Long id);

    Post savePost(Post post);

    Page<Post> findByUserOrderedByDatePageable(User user, int page);

    Page<Post> findAllOrderedByDatePageable(int page);

    void delete(Post post);







}
