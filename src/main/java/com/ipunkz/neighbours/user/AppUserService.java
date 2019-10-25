package com.ipunkz.neighbours.user;

public interface AppUserService {
  AppUser register(String username, String password, String password2) throws Exception;
  boolean isNameOccupied(String nickname);
}
