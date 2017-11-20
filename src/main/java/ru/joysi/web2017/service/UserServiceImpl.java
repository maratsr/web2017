package ru.joysi.web2017.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.joysi.web2017.exception.NotFoundException;
import ru.joysi.web2017.exception.UserNotActivatedException;
import ru.joysi.web2017.model.User;
import ru.joysi.web2017.repository.UserRepository;
import ru.joysi.web2017.security.AuthorizedUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    //private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByName(username);
        return user.map(u -> {
            if (!u.isActivated())
                throw new UserNotActivatedException("User " + username + " is not activated");
            List<GrantedAuthority> grantedAuthorities = u.getRoles().stream()
                    .map(auth -> new SimpleGrantedAuthority(auth.getName())).collect(Collectors.toList());
            return new AuthorizedUser(u.getName(), u.getPassword(), grantedAuthorities);
        }).orElseThrow(() -> new NotFoundException("user " + username + " not found!"));
    }

    @Override
    public void deleteUser(long id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}
