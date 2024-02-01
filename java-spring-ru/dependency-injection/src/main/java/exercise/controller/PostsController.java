package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{id}")
    public Post getById(@PathVariable Long id){
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %s not found.", id)));
    }

    @GetMapping
    public List<Post> getAll(){
        return postRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post){
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post){
        Post foundPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %s not found.", id)));
        foundPost.setBody(post.getBody());
        foundPost.setTitle(post.getTitle());
        return postRepository.save(foundPost);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Post toDeletingPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %s not found.", id)));
        commentRepository.deleteByPostId(id);
        postRepository.delete(toDeletingPost);
    }

}
// END
