package blog_007.app.util;

import blog_007.app.model.Post;
import org.springframework.data.domain.Page;

public class Pager {

    private Page<Post> post;

    public Pager(Page<Post> post) {
        this.post = post;
    }

    public int getPageIndex(){return post.getNumber();}

    public int getPageSize(){return post.getSize();}

    public boolean hasNext(){return post.hasNext();}

    public boolean hasPrevious(){return post.hasPrevious();}

    public int getTotalPage(){return post.getTotalPages();}

    public long getTotalElements(){return post.getTotalElements();}

    public Page<Post> getPost() {
        return post;
    }

    public boolean indexOutofbounds(){ return getPageIndex() < 0 || getPageIndex() > getTotalElements(); }
}
