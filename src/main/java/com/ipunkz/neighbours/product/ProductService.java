package com.ipunkz.neighbours.product;

import java.util.List;

public interface ProductService {
  void saveProduct(Product product);
  List<Product> listAllProducts();
  List<Product> listProductByKeyWord(String search);
}
