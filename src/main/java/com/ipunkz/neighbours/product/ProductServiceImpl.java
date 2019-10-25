package com.ipunkz.neighbours.product;

import com.ipunkz.neighbours.user.AppUser;
import com.ipunkz.neighbours.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

  private ProductRepository productRepository;
  private AppUserService appUserService;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository, AppUserService appUserService){
    this.productRepository = productRepository;
    this.appUserService = appUserService;
  }

  @Override
  public void saveProduct(Product product) {
    productRepository.save(product);
  }

  @Override
  public List<Product> listAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public List<Product> listProductBykeyWord(String search) {
    return productRepository.findAllByShortDescriptionContaining(search);
  }

  @Override
  public void addNewProduct(Product product, Long id) {
    AppUser user = appUserService.findById(id);
    product.setUser(user);
    productRepository.save(product);
  }
}
