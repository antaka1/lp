package ro.lsacbucuresti.lanpartyquiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DevicePlatform;
import org.springframework.mobile.device.LiteDeviceResolver;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.lsacbucuresti.lanpartyquiz.dto.RegisterUserDTO;
import ro.lsacbucuresti.lanpartyquiz.dto.UserDTO;
import ro.lsacbucuresti.lanpartyquiz.dto.UserLoginDTO;
import ro.lsacbucuresti.lanpartyquiz.security.JwtTokenUtil;
import ro.lsacbucuresti.lanpartyquiz.security.service.JwtAuthenticationResponse;
import ro.lsacbucuresti.lanpartyquiz.service.UserService;

import java.util.Collection;
import java.util.Map;

/**
 * Created by cristi on 08 - October - 2017
 */
@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO, Device device) throws BadCredentialsException{
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getUsername(),
                        userLoginDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userService.loadUserByUsername(userLoginDTO.getUsername());
        if(userDetails != null) {
            final String token = jwtTokenUtil.generateToken(userDetails, device);

            return ResponseEntity.ok(new JwtAuthenticationResponse(token, HttpStatus.OK.value()));
        } else {
            return null;
        }
    }

    @PostMapping("/loginWithFacebook")
    public ResponseEntity<?> loginWithFacebook(@RequestBody Map<String,String> input){
        String username = input.get("username").replace(" ","");
        String email = input.get("email");
        String password = input.get("password");

        UserDTO userDTO = userService.findByEmail(email);
        if(userDTO == null ){
            RegisterUserDTO registerUserDTO = new RegisterUserDTO();
            registerUserDTO.setEmail(email);
            registerUserDTO.setUsername(username);
            registerUserDTO.setFacebook(true);
            registerUserDTO.setPassword(password);
            userService.save(registerUserDTO);
            UserDetails userDetails = new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return null;
                }

                @Override
                public String getUsername() {
                    return username;
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
                    return true;
                }
            };

                final String token = jwtTokenUtil.generateToken(userDetails, getDevice());

                return ResponseEntity.ok(new JwtAuthenticationResponse(token,HttpStatus.OK.value()));

        } else {
            UserDetails userDetails = userService.loadUserByUsername(username);
            final String token = jwtTokenUtil.generateToken(userDetails, getDevice());
            return ResponseEntity.ok(new JwtAuthenticationResponse(token,HttpStatus.OK.value()));
        }
    }

    private Device getDevice(){
        return new Device() {
            @Override
            public boolean isNormal() {
                return true;
            }

            @Override
            public boolean isMobile() {
                return false;
            }

            @Override
            public boolean isTablet() {
                return false;
            }

            @Override
            public DevicePlatform getDevicePlatform() {
                return DevicePlatform.UNKNOWN;
            }
        };
    }
}
