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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String longDescription;
  private String shortDescription;
  private int price;
  private int bidLimit;
  private String owner;
  private String picturesName;
  private long startedAt;
  private long finishedAt;
  private boolean isExpired;

  @ManyToOne
  private AppUser user;

  public Product(String name, String longDescription, String shortDescription, int price, int bidLimit, int finishedAt, String picturesName) {
    this.name = name;
    this.longDescription = longDescription;
    this.shortDescription = shortDescription;
    this.price = price;
    this.bidLimit = bidLimit;
    this.owner = "";
    this.startedAt = java.time.Instant.now().getEpochSecond();
    this.finishedAt = startedAt + (finishedAt * 60);
    this.isExpired = false;
    this.picturesName = picturesName;
  }
}
