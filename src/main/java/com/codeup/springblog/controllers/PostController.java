package com.codeup.springblog.controllers;

import com.codeup.springblog.model.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private PostRepository postDao;
    private UserRepository usersDao;
    private EmailService emailService;

    public PostController(PostRepository postDao, UserRepository usersDao, EmailService emailService) {
        this.postDao = postDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

    //populate the postDao (which is a <List, Long>)
    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String viewAllPosts (Model model) {
//        ArrayList<Post> posts = (ArrayList<Post>) postDao.findAll();
        model.addAttribute("posts", postDao.findAll());
//        for(Post post: postDao.findAll()) {
//            System.out.println(post.getName());
//        }
        return "/posts/index";
    }


    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String viewIndividualPosts(@PathVariable long id, Model model) {
        model.addAttribute("singlePost", postDao.getById(id));
        return "/posts/show";
    }

//    @GetMapping("/posts/create")
//    public String showCreateForm(Model model) {
//        model.addAttribute("posts",new Post());
//        return "posts/create";
//    }

//    @ModelAttribute
//    @PostMapping("/posts/create")
//    public String submitPostParameters(@ModelAttribute Post posts) {
//        postDao.save(posts);
//        System.out.println(posts);
//        return "redirect:/posts";
//    }

//    @GetMapping("/posts/edit/{id}")
//    public String editPost(Model model, @PathVariable long id) {
//        model.addAttribute("posts",postDao.getById(id));
//        return "posts/create";
//    }

    @RequestMapping(path = "/posts/{id}/edit", method = RequestMethod.GET)

    public String viewEditPostForm(Model model, @PathVariable long id) {
        model.addAttribute("title", "Edit post");
        model.addAttribute("post", postDao.getById(id));
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String savePost(@ModelAttribute Post post) {
        post.setUsers(usersDao.getById(1L));
        postDao.save(post);
        emailService.prepareAndSend(post,"Post saved");
        return "redirect:/posts";
    }


}
