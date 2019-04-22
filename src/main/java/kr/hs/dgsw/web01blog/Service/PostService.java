package kr.hs.dgsw.web01blog.Service;



import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;

import java.util.List;

public interface PostService {
    List<PostUsernameProtocol> listAllPosts();
    PostUsernameProtocol addPost(Post post);
    PostUsernameProtocol updatePost(Long id, Post post);
    boolean deletePost(Long id);

    AttachmentProtocol getPostImageByID(Long id);
}
