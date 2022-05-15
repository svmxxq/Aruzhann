package kz.narxoz.narxozapp.repository;

import kz.narxoz.narxozapp.model.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEmailEndingWith(String email);              //1
    List<User> findTop2ByNameStartsWith(String name);            //2
    List<User> findBySurnameContaining(String surname);          //3
    List<User> findByEmailNotContaining(String email);           //7

User findByUsername(String username);

    //native query
    @Query(value = "select * from users order by id asc", nativeQuery = true)
    List<User> findUsersByCustomQuery(); //4

    @Query(value = "select * from users order by id desc limit 2", nativeQuery = true)
    List<User> findLastInsertedId();  //5

    @Query(value = "select * from users order by name desc", nativeQuery = true)
    List<User> findUsersOrderByNameDesc();  //6

    @Query(value = "select * from users where name=surname", nativeQuery = true)
    List<User> findByNameEqualsSurname();  //8

    @Query(value = "select * from users where email like '%narxoz.kz' or email like '%yandex.ru' or email like '%gmail.com'", nativeQuery = true)
    List<User> findByEmailContains();  //9

    @Query(value = "select distinct on (name) * from users", nativeQuery = true)
    List<User> findDistinctByName();   //10


}



