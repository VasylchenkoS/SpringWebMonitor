package com.vasilchenko.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version 1.0
 * @autor Viacheslav Vasilchenko
 */

@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "IndexPage");
        return "index";
    }

    @RequestMapping(value = "/page1")
    public String pageOne(Model model) {
        model.addAttribute("pageTitle", "Page1");
        return "page2";
    }

    @RequestMapping(value = "/page2")
    public String pageTwo(Model model) {
        model.addAttribute("pageTitle", "Page2");
        return "index";
    }
}