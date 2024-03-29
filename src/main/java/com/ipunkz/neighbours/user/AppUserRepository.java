package com.ipunkz.neighbours.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
  Optional<AppUser> findAppUserByNickname(String nickname);
  AppUser findAppUserById(Long userId);
}
