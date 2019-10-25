package com.ipunkz.neighbours.controller;

import com.ipunkz.neighbours.product.ProductRepository;
import com.ipunkz.neighbours.user.AppUser;
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
  private ProductRepository productRepository;

  @Autowired
  public MainController(AppUserService appUserService, ProductRepository productRepository) {
    this.appUserService = appUserService;
    this.productRepository = productRepository;
  }

  @GetMapping("/")
  public String getIndexPage(){
    return "index";
  }

  @PostMapping("/register")
  public String register(@RequestParam (name = "nickname") String nickname,
                         @RequestParam (name = "password") String password,
                         @RequestParam (name = "password2") String password2, Model model){
    try {
      AppUser user = appUserService.register(nickname,password,password2);
      return "redirect:/main" + user.getId();
    } catch (Exception e) {
      model.addAttribute("error",e.getMessage());
      return "index";
    }
  }

  @GetMapping("/main")
  public String renderMainPage(){
    return "main";
  }
}