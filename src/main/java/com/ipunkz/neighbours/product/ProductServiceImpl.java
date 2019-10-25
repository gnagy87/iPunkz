package com.ipunkz.neighbours.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

  private ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository){
    this.productRepository = productRepository;
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
}
