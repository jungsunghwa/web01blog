package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/list/user")
    public List<User> listAllUser(){
        return this.userService.listAllUser();
    }

    @PostMapping("/add/user")
    public User addUser(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @PutMapping("/update/user/{id}")
    public User updatePost(@PathVariable Long id, @RequestBody User user){
        return this.userService.updateUser(id,user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user){
        return  this.userService.loginUser(user);
    }

    @DeleteMapping("delete/user/{id}")
    public boolean deletePost(@PathVariable Long id) {
        return this.userService.deleteUser(id);
    }

}
