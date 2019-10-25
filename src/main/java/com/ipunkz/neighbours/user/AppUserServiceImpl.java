package com.ipunkz.neighbours.user;

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
  public AppUser register(String nickname, String password, String password2) throws Exception {
    if (isNameOccupied(nickname)) {
      throw new Exception("Username is already taken");
    }
    if (!password.equals(password2)) {
      throw new Exception("passwords do not match!");
    }
    AppUser user = new AppUser(nickname,password);
    appUserRepository.save(user);
    return user;
  }

  @Override
  public boolean isNameOccupied(String nickname) {
    return appUserRepository.findAppUserByNickname(nickname).isPresent();
  }
}
