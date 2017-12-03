package ro.lsacbucuresti.lanpartyquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.lsacbucuresti.lanpartyquiz.dto.PasswordResetDTO;
import ro.lsacbucuresti.lanpartyquiz.dto.RegisterUserDTO;
import ro.lsacbucuresti.lanpartyquiz.dto.UserDTO;
import ro.lsacbucuresti.lanpartyquiz.model.User;
import ro.lsacbucuresti.lanpartyquiz.repository.UserRepository;
import ro.lsacbucuresti.lanpartyquiz.security.JwtTokenUtil;

import javax.json.Json;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by cristi on 05 - October - 2017
 */
@Service
public class UserService implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserAuthoritiesService userAuthoritiesService;

    public UserDTO findOne(Long id,String username){
        User user = userRepository.findOne(id);
        User logged = userRepository.findByUsername(username);
        if(user != null && logged != null && user.getId().equals(logged.getId()) && user.isActive()){
            return UserDTO.fromUser(user);
        }
        return null;
    }

    public List<UserDTO> findAll(){
        List<User> all = (List<User>) userRepository.findAll();
        return all.stream().filter(User::isActive).map(user -> UserDTO.fromUser(user)).collect(Collectors.toList());
    }

    public String save(RegisterUserDTO user){
        try {
            User user1 = new User();
            user1.setEmail(user.getEmail());
            user1.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user1.setPhone(user.getPhone());
            user1.setUsername(user.getUsername());
            user1.setCreatedDate(new Date());
            if(user.isFacebook()){
                user1.setActive('Y');
                userRepository.save(user1);
            } else {
                user1.setActive('N');
                user1.setActivationToken(UUID.randomUUID().toString());
                logger.info("user token : "+user1.getActivationToken());
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(user1.getEmail());
                mailMessage.setSubject("[Lan Party Quiz App] Activation token");
                mailMessage.setText("Hello, please click the following link in order to activate your account http://localhost:4200/api/user/confirm/"+ user1.getActivationToken());
                userRepository.save(user1);
                mailSender.send(mailMessage);
            }
            return Json.createObjectBuilder()
                    .add("status","success").build().toString();
        }catch (Exception e){
            e.printStackTrace();
            return Json.createObjectBuilder().add("status","fail").build().toString();
        }
    }


    public String confirmUser(String token) {
        User user = userRepository.findByActivationToken(token);
        if(user != null){
            user.setActivationToken("");
            user.setActive('Y');
            userRepository.save(user);
            return "confirmed";
        }
        return "fail";
    }


    public String sendPasswordReset(String username) {
        User user = userRepository.findByUsername(username);
        user.setResetPasswordToken(UUID.randomUUID().toString());
        logger.info("user token reset password " + user.getResetPasswordToken());
        user.setResertSendDate(new Date());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("[Lan Party Quiz App] Reset your password");
        mailMessage.setText("Hello, please click the following link in order to reset your password. You have 2 hours to do this. "+ user.getResetPasswordToken());

        try{
            userRepository.save(user);
            mailSender.send(mailMessage);
            return "success";
        } catch (Exception e){
            return "fail";
        }
    }

    public String resertPassword(PasswordResetDTO passwordResetDTO) {
        User user = userRepository.findByResetPasswordToken(passwordResetDTO.getToken());
        if(user != null){
            user.setPassword(passwordResetDTO.getPassword());
            user.setResetPasswordToken("");
            try {
                userRepository.save(user);
                return "success";
            } catch (Exception e){
                return "fail";
            }
        }
        return "fail";
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if(user != null) {
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return userAuthoritiesService.getForUser(user) ;
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public String getUsername() {
                    return user.getUsername();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return user.isActive();
                }
            };
        }
        return null;
    }

    public UserDTO findByEmail(String email){
        User user = userRepository.findByEmail(email);
        return user == null ? null : UserDTO.fromUser(user);
    }

    public String validateToken(String username, String token) {
        if((username == null ||  username.isEmpty()) || (token == null || token.isEmpty())){
            return "invalid";
        }
        String tokenUsername = jwtTokenUtil.getUsernameFromToken(token);
        if(!username.equals(tokenUsername)){
            return "invalid";
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(jwtTokenUtil.validateToken(token,userDetails)){
            return "valid";
        }

        if(jwtTokenUtil.checkIfTokenIsExpired(token)){
            return "expired";
        }

        return "valid";

    }
}
