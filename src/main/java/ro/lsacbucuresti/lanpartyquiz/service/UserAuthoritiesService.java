package ro.lsacbucuresti.lanpartyquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import ro.lsacbucuresti.lanpartyquiz.model.Authority;
import ro.lsacbucuresti.lanpartyquiz.model.User;
import ro.lsacbucuresti.lanpartyquiz.model.UserAuthorities;
import ro.lsacbucuresti.lanpartyquiz.repository.UserAuthoritiesRepository;
import ro.lsacbucuresti.lanpartyquiz.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristi on 01 - November - 2017
 */
@Service
public class UserAuthoritiesService {

    @Autowired
    private UserAuthoritiesRepository userAuthoritiesRepository;
    @Autowired
    private UserRepository userRepository;

    public List<SimpleGrantedAuthority> getForUser(User user){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        List<UserAuthorities> authorities = userAuthoritiesRepository.getByUser(user);
        authorities.forEach(a -> {
            list.add(new SimpleGrantedAuthority(a.getAuthority().getRole().name()));
        });

        return list;
    }
}
