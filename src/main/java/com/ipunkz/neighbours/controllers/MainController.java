package com.ipunkz.neighbours.controllers;

import com.ipunkz.neighbours.exceptions.UserException;
import com.ipunkz.neighbours.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  private AppUserService appUserService;

  @Autowired
  public MainController(AppUserService appUserService) {
    this.appUserService = appUserService;
  }

  @GetMapping("/")
  public String homePage() {
    return "index";
  }

  @PostMapping("/login")
  public String loginUser(Model model, @RequestParam(value = "uname", required = false) String username,
                                       @RequestParam(value = "psw", required = false) String password) {
    try {
      return "redirect:/main" + appUserService.passwordCheck(username, password).getId();
    } catch (UserException err) {
      model.addAttribute("error", err.getMessage());
      return "index";
    }
  }
}
