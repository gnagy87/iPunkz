package com.ipunkz.neighbours.product;

import com.ipunkz.neighbours.user.AppUser;

import java.util.List;

public interface ProductService {
  void saveProduct(Product product);
  List<Product> listAllProducts();
  List<Product> listProductBykeyWord(String search);
  Product findById(Long productId);
  void auction(Long productId, AppUser user, String bid) throws Exception;
}
