package ro.lsacbucuresti.lanpartyquiz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.lsacbucuresti.lanpartyquiz.model.User;
import ro.lsacbucuresti.lanpartyquiz.model.UserAuthorities;

import java.util.List;

/**
 * Created by cristi on 01 - November - 2017
 */
@Repository
public interface UserAuthoritiesRepository extends CrudRepository<UserAuthorities,Long> {

    List<UserAuthorities> getByUser(User user);
}
