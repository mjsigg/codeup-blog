package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiceRollController {

//    @RequestMapping(path = "/roll-dice",method = RequestMethod.GET)
//    @ResponseBody
//    public String rollDice() {
//        return "rolldice";
//    }

    @GetMapping("/roll-dice")
    public String postResults(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{num}")
    @ResponseBody
    public String seeResults(@ PathVariable int num) {
        int i = (int) (Math.random() * 6) + 1;

        if(i == num) {
            return "Winner";
        }else {
            return "Opposite of winner.";
        }
    }



//    @GetMapping("/roll-dice")
//    public String (@RequestParam(name = "dice-input") String info, Model model) {
//        model.addAttribute("info", String.format("Did you hear? %s.",info));
//        return "/new-info";
//    }


}
