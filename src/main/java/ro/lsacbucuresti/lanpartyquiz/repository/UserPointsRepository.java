package ro.lsacbucuresti.lanpartyquiz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.lsacbucuresti.lanpartyquiz.dto.UserPointsDTO;
import ro.lsacbucuresti.lanpartyquiz.model.User;
import ro.lsacbucuresti.lanpartyquiz.model.UserPoints;

import java.util.List;

/**
 * Created by cristi on 05 - October - 2017
 */
@Repository
public interface UserPointsRepository extends CrudRepository<UserPoints,Long> {

    List<UserPoints> findByUser(User user);

    @Query("SELECT new ro.lsacbucuresti.lanpartyquiz.dto.UserPointsDTO(up.user.username,SUM(up.points)) from UserPoints up group by up.user order by sum(up.points) desc ")
    List<UserPointsDTO> findAllForTop();

}
