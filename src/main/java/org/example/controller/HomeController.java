package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dao.UserDao;
import org.example.dao.UserRDao;
import org.example.entity.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final UserDao userDao;


    @GetMapping("")
    public String addStudent(Model model) {
        model.addAttribute("userList", userDao.listAll());
        return "addStudent";
    }

    @GetMapping("addUserPage")
    public String user() {
        return "addUser";
    }

    @PostMapping("adduser")
    public String addUser(
                          @RequestParam String name,
                          @RequestParam String email,
                          @RequestParam String password
    ) {
        User user = new User(name, password, email);
        userDao.addObject(user);
        return "addUser";
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("userList", userDao.listAll());
        return "addStudent";
    }

    @GetMapping("user/delete/{id}")
    public String delete(Model model,
                         @PathVariable("id") int id){
        userDao.delete(id);
        model.addAttribute("userList", userDao.listAll());

        return "addStudent";
    }
//    @RequestMapping("user/update/{id}")
//    public String updateProduct(
//            Model model,
//            @PathVariable("id") int id,
//            @ModelAttribute UserRDao userRDao
//    ) {
//        User user = userDao.getUser(id);
//        user.setName(userRDao.getName());
//        user.setPassword(userRDao.getPassword());
//        user.setAddress(userRDao.getAddress());
//        userDao.update(user);
//        model.addAttribute("userList", userDao.listAll());
//
//        return list(model);
//
//    }

}
