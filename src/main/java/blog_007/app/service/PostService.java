package blog_007.app.service;

import blog_007.app.model.Post;
import blog_007.app.model.User;
import blog_007.app.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService implements IPostService {


    @Autowired
    private PostRepo postRepo;



    @Override
    public Optional<Post> findById(Long id) {
        return postRepo.findById(id);
     }

    @Override
    public Post savePost(Post post) {
        return postRepo.saveAndFlush(post);
    }

    @Override
    public Page<Post> findByUserOrderedByDatePageable(User user, int page) {
        return postRepo.findByUserOrderByCreatedateDesc(user,  PageRequest.of(subtractPageByOne(page),10));
    }

    @Override
    public Page<Post> findAllOrderedByDatePageable(int page) {
        return  postRepo.findAllByCreatedateOrderByCreatedateDesc(PageRequest.of(subtractPageByOne(page),10));
    }

    
    @Override
    public void delete(Post post) {
        postRepo.delete(post);
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }




}
