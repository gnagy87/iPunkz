package com.ipunkz.neighbours;

import com.ipunkz.neighbours.controller.UploadController;
import com.ipunkz.neighbours.upload.UploadHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@SpringBootApplication
@ComponentScan({"com.ipunkz.neighbours", "com.ipunkz.neighbours.upload"})
public class NeighboursApplication {

  public static void main(String[] args) {
    new File(UploadHandler.uploadDirectory);
    SpringApplication.run(NeighboursApplication.class, args);
  }

}
