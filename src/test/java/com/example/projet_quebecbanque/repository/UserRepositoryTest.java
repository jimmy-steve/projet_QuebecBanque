package com.example.projet_quebecbanque.repository;

import com.example.projet_quebecbanque.entities.User;
import com.example.projet_quebecbanque.utils.PasswordUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddNewUser() {
        User user = new User("frank","12345",false);

        PasswordUtils passwordUtils = new PasswordUtils();
        passwordUtils.hash(user.getHashPassword());
        User userHash = new User(user.getUsername(), passwordUtils.getHashpassword(), passwordUtils.getSalt());
        userRepository.save(userHash);

        User savedUser = userRepository.save(userHash);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getIdUser()).isGreaterThan(0);

    }

    @Test
    public void testAddNewAdmnin() {
        User user = new User("admin","admin",true);

        PasswordUtils passwordUtils = new PasswordUtils();
        passwordUtils.hash(user.getHashPassword());
        User userHash = new User(user.getUsername(), passwordUtils.getHashpassword(), passwordUtils.getSalt(),
                user.isAdmin());
        userRepository.save(userHash);

        User savedUser = userRepository.save(userHash);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getIdUser()).isGreaterThan(0);


    }
}
