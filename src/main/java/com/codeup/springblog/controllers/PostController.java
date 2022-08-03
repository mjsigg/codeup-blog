package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String justPost () {
        return "Post Malone, no";
    }

    @RequestMapping(value = "/posts/{var1}", method = RequestMethod.GET)
    @ResponseBody

    public String post(@PathVariable String var1) {

        if (var1.equals("create")) {
            return "create";

        }else

            return "<h1> h to the izzo </h1>";
    }

}
