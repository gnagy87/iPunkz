package com.ipunkz.neighbours.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadHandler {

  public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/webapp/pics";


  public String savePics(MultipartFile file) {
    StringBuilder fileName = new StringBuilder();
    Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
    fileName.append(file.getOriginalFilename());
    try {
      Files.write(fileNameAndPath, file.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "/pics/" + file.getOriginalFilename();
  }
}
