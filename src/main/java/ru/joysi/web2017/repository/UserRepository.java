package ru.joysi.web2017.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.joysi.web2017.model.User;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByName(String name);

    //List<User> findAllByOrderByNameAsc(Sort sort);


}
