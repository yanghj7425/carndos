package com.yhj.web.controller.user;

import com.yhj.web.controller.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
    @PostMapping("info'")
    public ModelMap getUserInfo(ModelMap modelMap) {
        System.out.println(modelMap);
        return modelMap;
    }
}
