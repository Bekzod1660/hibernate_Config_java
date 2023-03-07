package uz.pdp.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.dao.UserDao;
import uz.pdp.dto.UserDto;
import uz.pdp.entity.UserEntity;

@Controller
@RequestMapping("/auth/")
public class AuthController {
    private final UserDao userDao;

    public AuthController(UserDao userDao) {
        this.userDao = userDao;
    }
    @GetMapping("register")
    public String register(){
        return "auth/register";
    }

    @PostMapping("register")
    public String add(@ModelAttribute UserEntity userDto, Model model){
        UserEntity user = userDao.add(userDto);
        model.addAttribute("text1",user.getFullName()+"'s personal cabinet.");
        model.addAttribute("user",user);
        return "user/cabinet";
    }
    @GetMapping("login")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        return "";
    }
}
