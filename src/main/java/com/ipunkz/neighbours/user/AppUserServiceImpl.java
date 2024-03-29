package com.ipunkz.neighbours.user;

import com.ipunkz.neighbours.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

  private AppUserRepository appUserRepository;

  @Autowired
  public AppUserServiceImpl(AppUserRepository appUserRepository) {
    this.appUserRepository = appUserRepository;
  }

  @Override
  public AppUser passwordCheck(String username, String password) throws UserException {
    Optional<AppUser> user = appUserRepository.findAppUserByNickname(username);
    user.orElseThrow(() -> new UserException("User does not exist!"));
    if (user.get().getPassword().equals(password)){
      return user.get();
    }
    throw new UserException("Password is not correct");
  }

  @Override
  public AppUser findById(Long userId) {
    return appUserRepository.findAppUserById(userId);
  }

  @Override
  public AppUser setProfileImg(Long id, String picturesPath) {
    AppUser appUser = findById(id);
    appUser.setPictureName(picturesPath);
    appUserRepository.save(appUser);
    return appUser;
  }

  @Override
  public void saveAppUser(AppUser user) {
    appUserRepository.save(user);
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
