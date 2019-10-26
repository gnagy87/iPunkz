package com.ipunkz.neighbours.controller;

import com.ipunkz.neighbours.exceptions.UserException;
import com.ipunkz.neighbours.product.Product;
import com.ipunkz.neighbours.product.ProductService;
import com.ipunkz.neighbours.time.TimeService;
import com.ipunkz.neighbours.upload.UploadHandler;
import com.ipunkz.neighbours.user.AppUser;
import com.ipunkz.neighbours.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

  private AppUserService appUserService;
  private ProductService productService;
  private UploadHandler uploadHandler;
  private TimeService timeService;

  @Autowired
  public MainController(AppUserService appUserService, ProductService productService, UploadHandler uploadHandler, TimeService timeService) {
    this.appUserService = appUserService;
    this.productService = productService;
    this.uploadHandler = uploadHandler;
    this.timeService = timeService;
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
      model.addAttribute("user", user);
      model.addAttribute("userId",user.getId());
      return "home";
    } catch (Exception e) {
      model.addAttribute("error",e.getMessage());
      return "index";
    }
  }

  @PostMapping("/login")
  public String loginUser(Model model, @RequestParam(value = "uname", required = false) String username,
                          @RequestParam(value = "psw", required = false) String password) {
    try {
      model.addAttribute("user", appUserService.passwordCheck(username, password));
      return "home";
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

  @GetMapping("/product/{productId}")
  public String detailsOfProduct(@PathVariable (value = "productId") Long productId,
                                 @RequestParam (value = "userId") Long userId, Model model) {
    model.addAttribute("userId", userId);
    model.addAttribute("product", productService.findById(productId));
    return "product";
  }

  @PostMapping("/product/{productId}")
  public String makeBid(@RequestParam (value = "bid") String bid,
                        @RequestParam (value = "userId") Long userId,
                        @PathVariable (value = "productId") Long productId, Model model) {
    Product product = productService.findById(productId);
    if (timeService.expiredOrNOt(product.getFinishedAt()) > 0){
      product.setExpired(true);
      productService.saveProduct(product);
      model.addAttribute("product", product);
      return "product";
    }
    try {
      AppUser user = appUserService.findById(userId);
      productService.auction(productId,user,bid);
    } catch (Exception e) {
      model.addAttribute("error",e.getMessage());
    }
    model.addAttribute("userId", userId);
    model.addAttribute("product", productService.findById(productId));
    return "product";
  }

  @PostMapping("/addProduct")
  public String addNewProduct(@RequestParam("name") String name, @RequestParam("long") String lDesc,
                              @RequestParam("short") String sDesc, @RequestParam("price") int price,
                              @RequestParam("limit") int bidLimit, @RequestParam("expire") int expire,
                              @RequestParam("upload")MultipartFile file, @RequestParam("userid") long id, Model model) {

    Product product = new Product(name, lDesc, sDesc, price, bidLimit, expire, uploadHandler.savePics(file));
    productService.addNewProduct(product, id);
    model.addAttribute("user", appUserService.findById(id));
    return "home";
  }
}