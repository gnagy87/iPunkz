package com.ipunkz.neighbours.user;

import com.ipunkz.neighbours.exceptions.UserException;

public interface AppUserService {

  AppUser passwordCheck(String username, String password) throws UserException;
}
