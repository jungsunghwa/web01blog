package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web01blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/list/post")
    public List<PostUsernameProtocol> list(){
        return this.postService.listAllPosts();
    }

    @PostMapping("/add/post")
    public PostUsernameProtocol addPost(@RequestBody Post post){
        return this.postService.addPost(post);
    }

    @PutMapping("/update/post/{id}")
    public PostUsernameProtocol updatePost(@PathVariable Long id, @RequestBody Post post){
        return this.postService.updatePost(id,post);
    }

    @DeleteMapping("delete/post/{id}")
    public boolean deletePost(@PathVariable Long id){
        return this.postService.deletePost(id);
    }

}
