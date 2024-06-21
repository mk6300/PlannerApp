package com.plannerapp.service.impl;

import com.plannerapp.model.RegisterDTO;
import com.plannerapp.model.UserDTO;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepo;
import com.plannerapp.service.UserService;
import com.plannerapp.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final LoggedUser loggedUser;
    private final HttpSession session;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder encoder, LoggedUser loggedUser, HttpSession session) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.loggedUser = loggedUser;
        this.session = session;
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        User user = this.getUserByUsername(username);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = this.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        return encoder.matches(password, user.getPassword());
    }

    @Override
    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void register(RegisterDTO registerDTO) {
        this.userRepo.save(this.mapUser(registerDTO));
        this.login(registerDTO.getUsername());
    }

    @Override
    public void logout() {
        this.session.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }

    private User getUserByUsername(String username) {
        return this.userRepo.findByUsername(username).orElse(null);
    }

    private User mapUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        return user;
    }

    private UserDTO mapUserDTO(User user) {
        return new UserDTO()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername());
    }

    public void initAdmin() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("1234"));
        admin.setEmail("admin@abv.bg");
        userRepo.save(admin);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepo.findById(id);

    }

    public void initTest() {
        User test = new User();
        test.setUsername("testUser");
        test.setPassword(encoder.encode("1234"));
        test.setEmail("test@abv.bg");
        userRepo.save(test);
    }


//    public void removeAllWords() {
//        List<User> all = userRepo.findAll();
//        all.stream().forEach(e -> {
//            e.getAddedWords().clear();
//            userRepo.save(e);
//        });
//    }

}