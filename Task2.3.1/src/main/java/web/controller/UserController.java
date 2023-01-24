package web.controller;

import net.bytebuddy.matcher.StringMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String startedPage(){
        return "/index";
    }
    @GetMapping(value = "/users")
    public  String showAllUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "/users/showAllUser";
    }
@GetMapping("/users/new")
    public String newUser(Model model){
        model.addAttribute("user",new User());
        return "/users/createNewUser";
    }
    @PostMapping("/users")
   public String createNewUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("/users/editUsers")
    public String editUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "users/editUsers";
    }
    @GetMapping("/users/{id}")
    public String showUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user",userService.getUser(id));
        return "users/showUser";
    }
    @GetMapping("users/{id}/edit")
    public String editUser(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.getUser(id));
        return "users/edit";
    }
    @PatchMapping("users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id){
        userService.updateUser(user);
        return "redirect:/users";
    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.removeUser(id);
        return "redirect:/users";
    }
}
