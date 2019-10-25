package com.ipunkz.neighbours.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String description;
  private int quantity;
  private int price;

  public Product(String name, String description, int quantity, int price) {
    this.name = name;
    this.description = description;
    this.quantity = quantity;
    this.price = price;
  }
}
