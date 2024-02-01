package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{id}")
    public PostDTO getById(@PathVariable long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %s not found", id)));
        return toPostDTO(post);
    }

    @GetMapping
    public List<PostDTO> getAll(){
        List<Post> posts = postRepository.findAll();
        List<Comment> comments = commentRepository.findAll();
        return posts.stream()
                .map(this::toPostDTO)
                .toList();
    }

    private CommentDTO toCommentsDTO(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setBody(comment.getBody());
        commentDTO.setId(comment.getId());
        return  commentDTO;
    }

    private PostDTO toPostDTO(Post post){

        List<Comment> postComments = commentRepository.findByPostId(post.getId());

        PostDTO postDTO = new PostDTO();
        postDTO.setBody(post.getBody());
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setComments(postComments.stream()
                .map(this::toCommentsDTO)
                .toList());
        return postDTO;
    }

}
// END
