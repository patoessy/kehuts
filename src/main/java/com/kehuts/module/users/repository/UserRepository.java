package com.kehuts.module.users.repository;

import com.kehuts.module.security.Authtools;
import com.kehuts.module.users.model.User;
import com.kehuts.module.users.model.dto.UserDto;
import com.kehuts.module.utils.commonutils.CommonUtility;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User getByEmail(String username) {
        return find("from User where email = ?1", username).firstResult();
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return find("from User where password = ?1 and email = ?2",
                password,
                email).firstResult();
    }


    public User getById(Long id) {
        return findById(id);
    }

    public void addUserRefreshToken(UserDto user){
        LocalDateTime expireDate = CommonUtility.getDaysAhead(60);
        //System.out.println("Date expiry : " + expireDate);
        Parameters p = Parameters.with("token", user.refreshToken())
                .and("validity", expireDate)
                .and("email", user.email());
        update("update User SET refreshToken=:token, refreshTokenValidity=:validity WHERE email=:email", p);
    }
}
