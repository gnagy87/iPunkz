package com.ipunkz.neighbours.user;

import com.ipunkz.neighbours.exceptions.UserException;

public interface AppUserService {
  AppUser register(String username, String password, String password2) throws Exception;

  boolean isNameOccupied(String nickname);

  AppUser passwordCheck(String username, String password) throws UserException;

  AppUser findById(Long userId);

  AppUser setProfileImg(Long id, String picturesPath);
}
