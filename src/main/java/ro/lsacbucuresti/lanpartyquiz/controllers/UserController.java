package ro.lsacbucuresti.lanpartyquiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ro.lsacbucuresti.lanpartyquiz.dto.PasswordResetDTO;
import ro.lsacbucuresti.lanpartyquiz.dto.RegisterUserDTO;
import ro.lsacbucuresti.lanpartyquiz.dto.UserDTO;
import ro.lsacbucuresti.lanpartyquiz.dto.UserPointsDTO;
import ro.lsacbucuresti.lanpartyquiz.service.UserPointsService;
import ro.lsacbucuresti.lanpartyquiz.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by cristi on 05 - October - 2017
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPointsService userPointsService;

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id) {
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userService.findOne(id,username);
    }

    @PostMapping(value = "/register", produces = "application/json")
    @ResponseBody
    public String registerUser(@RequestBody RegisterUserDTO user){
         return userService.save(user);
    }

    @GetMapping("/confirm/{token}")
    public String confirmUser(@PathVariable("token") String token){
        return userService.confirmUser(token);
    }

    @GetMapping("/points/{id}")
    public Integer getTotalPoints(@PathVariable("id") Long id){
        //TODO check if user is the one logged in
        return userPointsService.getUserPoints(id);
    }

    @GetMapping("/top10")
    public List<UserPointsDTO> getTop10(){
        return userPointsService.getTop10();
    }

    @PostMapping("/passwordReset")
    public String sendPasswordReset(@RequestBody String username){
        return userService.sendPasswordReset(username);
    }

    @GetMapping("/resetPassword")
    public String resertPassword(@RequestBody PasswordResetDTO passwordResetDTO){
        return userService.resertPassword(passwordResetDTO);
    }

    @PostMapping("/validateToken")
    public String validateToken(@RequestBody Map<String,String> input){
        String username = input.get("username");
        String token = input.get("token");
        return userService.validateToken(username,token);
    }
}
