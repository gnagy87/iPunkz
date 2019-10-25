package com.ipunkz.neighbours.controller;

import com.ipunkz.neighbours.exceptions.UserException;
import com.ipunkz.neighbours.product.ProductService;
import com.ipunkz.neighbours.user.AppUser;
import com.ipunkz.neighbours.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  private AppUserService appUserService;
  private ProductService productService;

  @Autowired
  public MainController(AppUserService appUserService, ProductService productService) {
    this.appUserService = appUserService;
    this.productService = productService;
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
      return "redirect:/main/?id=" + user.getId();
    } catch (Exception e) {
      model.addAttribute("error",e.getMessage());
      return "index";
    }
  }

  @PostMapping("/login")
  public String loginUser(Model model, @RequestParam(value = "uname", required = false) String username,
                          @RequestParam(value = "psw", required = false) String password) {
    try {
      return "redirect:/main/?id=" + appUserService.passwordCheck(username, password).getId();
    } catch (UserException err) {
      model.addAttribute("error", err.getMessage());
      return "index";
    }
  }

  @GetMapping("/main")
  public String renderMainPage(@RequestParam (value = "id") Long userId, Model model){
    model.addAttribute("user", appUserService.findById(userId));
    return "home";
  }

  @GetMapping("/auction/{id}")
  public String renderAuctionPage(@PathVariable(value = "id") Long userId, @RequestParam (value = "search", required = false) String search, Model model) {
    model.addAttribute("userId", userId);
    if (search != null) {
      model.addAttribute("products", productService.listProductByKeyWord(search));
      return "auction";
    }
    model.addAttribute("products", productService.listAllProducts());
    return "auction";
  }
}