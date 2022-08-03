package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MathController {
    @RequestMapping(path = "/{operator}/{num1}/{operand}/{num2}", method = RequestMethod.GET)
    @ResponseBody

    public String findOperator(@PathVariable String operator, @PathVariable int num1, @PathVariable int num2) {

        if (operator.equals("add")) {
            int answer = num1 + num2;
            return String.format("<h1> %d + %d = %d  </h1> \n", num1, num2, answer);

        } else if (operator.equals("subtract")) {
            int answer = num1 - num2;
            return String.format("<h1> %d - %d = %d  </h1> \n", num1, num2, answer);

        } else if (operator.equals("multiply")) {
            int answer = num1 * num2;
            return String.format("<h1> %d * %d = %d  </h1> \n", num1, num2, answer);

        } else if (operator.equals("divide")) {
            int answer = num1 / num2;
            return String.format("<h1> %d / %d = %d  </h1> \n", num1, num2, answer);
        }

        return "what?";
    }

}
