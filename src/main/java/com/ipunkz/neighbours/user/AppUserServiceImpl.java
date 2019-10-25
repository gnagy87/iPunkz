package com.ipunkz.neighbours.user;

import com.ipunkz.neighbours.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

  private AppUserRepository appUserRepository;

  @Autowired
  public AppUserServiceImpl(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public AppUser passwordCheck(String username, String password) throws UserException {
    AppUser user = appUserRepository.findByNickname(username);
    if (user.getPassword().equals(password)){
      return user;
    }
    throw new UserException("Password or username is not correct");
  }
}
