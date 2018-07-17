package com.yhj.web.controller.index;

import com.yhj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends BaseController {


    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("welcome", "这是一个欢迎的 header");
        model.addAttribute("tel","125464565464");
        logger.debug("message{}", model);
        return "index";
    }

}
