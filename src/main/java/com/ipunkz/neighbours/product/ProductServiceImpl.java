package com.ipunkz.neighbours.product;

import com.ipunkz.neighbours.user.AppUser;
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

  @Override
  public Product findById(Long productId) {
    return productRepository.findById(productId).get();
  }

  @Override
  public void auction(Long productId, AppUser user, String bid) throws Exception {
    Product product = productRepository.findById(productId).get();
    if (bid == null || bid.equals("") || Integer.parseInt(bid) < product.getPrice() + product.getBidLimit()) {
      throw new Exception("Not enough money");
    }
      product.setOwner(user.getNickname());
      product.setPrice(Integer.parseInt(bid));
      productRepository.save(product);
  }
}
