package blog_007.app.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Long id;


    @Size(min = 2, message = "comments length between 2 to 2000")
    private String comment_body;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column(name = "comments_date", nullable = false, updatable = false)
    private Date comments_date;


    @ManyToOne
    @JoinColumn(name = "post__id", referencedColumnName = "post_id", nullable = false)
    private Post post;


    @ManyToOne
    @JoinColumn(name = "user__id", referencedColumnName = "id", nullable = false)
    private User user;


    public Comment() { }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment_body() {
        return comment_body;
    }

    public void setComment_body(String comment_body) {
        this.comment_body = comment_body;
    }

    public Date getComments_date() {
        return comments_date;
    }

    public void setComments_date(Date comments_date) {
        this.comments_date = comments_date;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
