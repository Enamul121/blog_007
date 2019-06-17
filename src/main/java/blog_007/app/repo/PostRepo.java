package blog_007.app.repo;

import blog_007.app.model.Post;
import blog_007.app.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {

     Page<Post> findByUserOrderByCreatedateDesc(User user, Pageable pageable);

    Page<Post> findAllByCreatedateOrderByCreatedateDesc(Pageable pageable);


    @Query(value = "select  p from post p order by post_id desc ")
    List<Post> getAllByIdAndOrderByIdBodyDesc();


      Optional<Post> findById(Long id);
    }
