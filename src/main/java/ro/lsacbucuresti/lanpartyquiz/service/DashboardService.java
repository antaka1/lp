package ro.lsacbucuresti.lanpartyquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.lsacbucuresti.lanpartyquiz.dto.UserDashDTO;
import ro.lsacbucuresti.lanpartyquiz.model.Question;
import ro.lsacbucuresti.lanpartyquiz.model.User;
import ro.lsacbucuresti.lanpartyquiz.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by cristi on 28 - October - 2017
 */
@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPointsService userPointsService;
    @Autowired
    private QuestionService questionService;


    public List<UserDashDTO> getUsers() {
        List<User> allUsers = (List<User>) userRepository.findAll();
        return allUsers.stream().filter(u -> u.isActive()).map(u ->
            UserDashDTO.fromUser(u,userPointsService.getUserPoints(u.getId()))).collect(Collectors.toList());
    }

    public List<Question> getAllQuestion(){
        return questionService.findAll();
    }
}
