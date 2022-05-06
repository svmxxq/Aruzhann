package kz.narxoz.springapp;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;

    import java.util.List;
    public interface UserRepository extends JpaRepository<User, Long> {
        ///tap 1
        User findByUsername(String username);

        List <User> findByEmailContainingOrderByNameDesc(String email);
        //tap 2

        List<User> findTop2ByNameStartingWith (String name);
        //tap 3

        List<User> findBySurnameContaining(String surname);



        //tap 4

        @Query(value="select *from users order by id asc " , nativeQuery = true)
        List<User> findByIdOrderById(Long id);

        //tap 5

        @Query(value= "select *from users order by id desc limit(2)", nativeQuery = true)
        List<User> findshowlastUsers();
        //  tap 6

        @Query(value= "select *from users order by name desc", nativeQuery = true)
        List<User> sortByName();

        //tap 7
//        @Query( value="select *from users where email not like'%@%'", nativeQuery = true)
        List <User> findByEmailNotContaining(String email2);

        // tap 8
        @Query(value= "select *from users where name=surname",nativeQuery = true)
        List<User>EqualNameSurname(String name1);

        //tap 9
        @Query(value= "select *from users where email like '%narxoz.kz%' or email like '%gmail.com%' or email like '%yandex.ru%' ",
                nativeQuery = true)
        List<User> emailLike();
        //tap 10
        @Query(value="select distinct on (name) * from users ", nativeQuery = true)
        List<User> findDistinctByName();
    }


