package com.ipunkz.neighbours.user;

import com.ipunkz.neighbours.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nickname;
  private String password;
  private String pictureName;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private List<Product> products;

  public AppUser(String nickname, String password) {
    this.nickname = nickname;
    this.password = password;
  }
}
