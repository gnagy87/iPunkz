package com.ipunkz.neighbours.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

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

  public AppUser(String nickname, String password) {
    this.nickname = nickname;
    this.password = password;
  }
}
