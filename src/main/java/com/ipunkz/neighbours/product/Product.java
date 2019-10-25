package com.ipunkz.neighbours.product;

import com.ipunkz.neighbours.user.AppUser;
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
  private String longDescription;
  private String shortDescription;
  private int price;
  private int bidLimit;
  private String owner;
  private String picturesName;

  @ManyToOne
  private AppUser user;

  public Product(String name, String longDescription, String shortDescription, int price, int bidLimit) {
    this.name = name;
    this.longDescription = longDescription;
    this.shortDescription = shortDescription;
    this.price = price;
    this.bidLimit = bidLimit;
    this.owner = null;
  }
}
