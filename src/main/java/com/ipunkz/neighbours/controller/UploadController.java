package com.ipunkz.neighbours.controller;

import com.ipunkz.neighbours.upload.UploadHandler;
import com.ipunkz.neighbours.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UploadController {

  private AppUserService appUserService;
  private UploadHandler uploadHandler;

  @Autowired
  public UploadController(AppUserService appUserService, UploadHandler uploadHandler) {
    this.appUserService = appUserService;
    this.uploadHandler = uploadHandler;
  }

  public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/pics";

  @PostMapping("/upload")
  public String uploading(Model model, @RequestParam("userid") Long id, @RequestParam("upload")MultipartFile file) {
    appUserService.setProfileImg(id, uploadHandler.savePics(file));
    model.addAttribute("user", appUserService.findById(id));
    return "home";
  }
}
