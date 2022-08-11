package com.example.login_SignUp_Authentication.repository;


import com.example.login_SignUp_Authentication.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/** NOTE: The annotation make sure it's a springframework
 * and spring security annotation
 * **/

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
