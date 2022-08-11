package com.example.login_SignUp_Authentication.service;

import com.example.login_SignUp_Authentication.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**AppUserService  class implement the userServiceDetails interface
 * This class enable us to find user when they try to log in
 * This class connect the repository (note the repository is an interface class)
 * **/

@Service
@AllArgsConstructor // This annotation helps lombok to internally implement the AppUserRepository constructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private  final AppUserRepository appUserRepository;
// implement constructor
//    public AppUserService(AppUserRepository appUserRepository) {
//        this.appUserRepository = appUserRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(()->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
}
