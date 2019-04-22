package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PostUsernameProtocol> listAllPosts() {
        List<Post> postList = this.postRepository.findAll();
        List<PostUsernameProtocol> cupList = new ArrayList<>();
        postList.forEach(post -> {
            cupList.add(changePostToProtocol(post));
        });
        return cupList;
    }

    @Override
    public PostUsernameProtocol addPost(Post post) {
        post = this.postRepository.save(post);

        return changePostToProtocol(post);
    }

    @Override
    @Transactional
    public PostUsernameProtocol updatePost(Long id, Post post) {
        return this.postRepository
                .findById(id)
                .map(found -> {
                    found.setUserID(Optional.ofNullable(post.getUserID()).orElse(found.getUserID()));
                    found.setContent(Optional.ofNullable(post.getContent()).orElse(found.getContent()));
                    found.setImageOriginalName(Optional.ofNullable(post.getImageOriginalName()).orElse(found.getImageOriginalName()));
                    found.setImagePath(Optional.ofNullable(post.getImagePath()).orElse(found.getImagePath()));
                    return changePostToProtocol(this.postRepository.save(found));
                })
                .orElse(null);
    }

    @Override
    public boolean deletePost(Long id) {
        return this.postRepository.findById(id).map(found -> {
            this.postRepository.delete(found);
            return true;
        }).orElse(false);
    }

    @Override
    public AttachmentProtocol getPostImageByID(Long id) {
        return this.postRepository
                .findById(id)
                .map(found -> new AttachmentProtocol(
                        found.getImagePath(),
                        found.getImageOriginalName()))
                .orElse(null);
    }

    private PostUsernameProtocol changePostToProtocol(Post post){
        Optional<User> found = this.userRepository.findById(post.getUserID());

        String username = (found.isPresent()) ? found.get().getName() : null;

        return new PostUsernameProtocol(post, username);
    }

}
