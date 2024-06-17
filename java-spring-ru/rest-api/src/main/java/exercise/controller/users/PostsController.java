package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index(@PathVariable String id) {
        var usersPosts = posts.stream().filter(p -> (p.getUserId() == Integer.valueOf(id))).toList();
        return usersPosts;
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable String id, @RequestBody Post post) {
        var newPost = new Post();
        newPost.setUserId(Integer.valueOf(id));
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());
        newPost.setSlug(post.getSlug());

        posts.add(newPost);

        return newPost;
    }
}

// END
