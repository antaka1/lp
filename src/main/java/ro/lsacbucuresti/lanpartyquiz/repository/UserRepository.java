package ro.lsacbucuresti.lanpartyquiz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.lsacbucuresti.lanpartyquiz.model.User;

/**
 * Created by cristi on 05 - October - 2017
 */

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);

    User findByActivationToken(String activationToken);

    User findByResetPasswordToken(String resetPasswordToken);

    User findByEmail(String email);
}
