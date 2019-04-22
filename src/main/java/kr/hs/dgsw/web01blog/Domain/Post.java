package kr.hs.dgsw.web01blog.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private Long userID;
    private String content;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime modifined;

    private String imagePath;
    private String imageOriginalName;

    public Post(Long userID, String content) {
        this.userID = userID;
        this.content = content;
    }

    public Post(Long userID, String content, String imagePath, String imageOriginalName) {
        this.userID = userID;
        this.content = content;
        this.imagePath = imagePath;
        this.imageOriginalName = imageOriginalName;
    }

    public Post(Post post) {
        this.id = post.getId();
        this.userID = post.getUserID();
        this.content = post.getContent();
        this.created = post.getCreated();
        this.modifined = post.getModifined();
        this.imageOriginalName = post.getImageOriginalName();
        this.imagePath = post.getImagePath();
    }

    public void changePostData(Post post) {
        if (post == null) return;
        if (post.content != null) this.content = post.content;
        if (post.imagePath != null) this.imagePath = post.imagePath;
        if (post.imageOriginalName != null) this.imageOriginalName = post.imageOriginalName;
    }
}
