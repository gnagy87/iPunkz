package com.ipunkz.neighbours.product;

import com.ipunkz.neighbours.user.AppUser;
import com.ipunkz.neighbours.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
  public List<Product> listProductByKeyWord(String search) {
    List<Product> products = productRepository.findAll();
    return products.stream().filter(x -> x.getShortDescription().contains(search.toLowerCase())
    || x.getName().contains(search.toLowerCase())).collect(Collectors.toList());
  }

  @Override
  public void addNewProduct(Product product, Long id) {
    AppUser user = appUserService.findById(id);
    product.setUser(user);
    productRepository.save(product);
  }
}
