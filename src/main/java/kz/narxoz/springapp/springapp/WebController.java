package kz.narxoz.springapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Controller
public class WebController {


    @Autowired
    UserRepository repository;


    @GetMapping("/")
    public String showUserList(@RequestParam(name="email", required=false, defaultValue="") String email,
                               @RequestParam(name="email1", required=false, defaultValue="") String email1,

                               @RequestParam(name="name", required=false, defaultValue="") String name,
                               @RequestParam(name="name1", required=false, defaultValue="") String name1,
                               @RequestParam(name="id", required=true, defaultValue="") Long id,
                               @RequestParam(name = "surname", required = false, defaultValue = "") String surname,

                               Model model) {
        List<User> users = repository.findAll();
        // tap 1
        if(!email.isEmpty()){
            users=repository.findByEmailContainingOrderByNameDesc(email);
        }

        //tap 2
        if(!name.isEmpty()){
            users=repository.findTop2ByNameStartingWith(name);
        }

        //tap 3
        if(!surname.isEmpty()){
            users = repository.findBySurnameContaining(surname);}

        // tap 4
        if(id!=null){
            users=repository.findByIdOrderById(id);
        }
        // tap 8
        if(!name1.isEmpty()){
            users=repository.EqualNameSurname(name1);
        }
        // tap 9
        if(!email1.isEmpty()){
            users=repository.emailLike();
        }
        model.addAttribute("users", users);
        return "index";
    }


    // 5 tap
    @GetMapping("/5")
    public String showUserList(@RequestParam(name = "id", required = false, defaultValue = "") Long id,
                               Model model){
        List<User> users = repository.findshowlastUsers();
        model.addAttribute("users", users);
        return "index";
    }
    // 6 tap
    @GetMapping("/sort")
    public String sortedByname(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                               Model model){
        List<User> users = repository.sortByName();
        model.addAttribute("users", users);
        return "index";
    }

    // 7 tap
    @GetMapping("/not")
    public String email(@RequestParam(name = "email2", required = false, defaultValue = "") String email2,
                        Model model){
        List<User> users = repository.findAll();

        users = repository.findByEmailNotContaining(email2);
        model.addAttribute("users", users);
        return "index";
    }

    //10 tap
    @GetMapping("/distinct")
    public String distinct(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                           Model model){
        List<User> users = repository.findAll();
        users = repository.findDistinctByName();
        model.addAttribute("users", users);
        return "index";
    }






    @PostMapping("/adduser")
    public String createUser(@ModelAttribute User user){
        addUser(user);
        return "redirect:/";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, User user) {
        updateUser(user);
        return "redirect:/";
    }

   @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
      deleteById(id);
       return "redirect:/"; }



    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        User user= repository.getById(id);
        model.addAttribute("user", user);
        return "update-user";
    }
    private void deleteById(long id) {
     repository.deleteById(id);
    }
    private void addUser(User newUser) {
     repository.save(newUser);
    }

    private void updateUser(User updateUser) {
        User user = repository.getById(updateUser.getId());

        user.setName(updateUser.getName());
        user.setSurname(updateUser.getSurname());
        user.setEmail(updateUser.getEmail());
        repository.save(user);
    }
}
