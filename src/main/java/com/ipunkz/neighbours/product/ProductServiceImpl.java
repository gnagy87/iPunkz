package com.ipunkz.neighbours.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
  public List<Product> listProductByKeyWord(String search) {
    List<Product> products = productRepository.findAll();
    return products.stream().filter(x -> x.getShortDescription().contains(search.toLowerCase())
    || x.getName().contains(search.toLowerCase())).collect(Collectors.toList());
  }
}
