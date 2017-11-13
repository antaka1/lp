package ro.lsacbucuresti.lanpartyquiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.lsacbucuresti.lanpartyquiz.dto.UserPointsDTO;
import ro.lsacbucuresti.lanpartyquiz.model.Question;
import ro.lsacbucuresti.lanpartyquiz.model.User;
import ro.lsacbucuresti.lanpartyquiz.model.UserPoints;
import ro.lsacbucuresti.lanpartyquiz.repository.QuestionRepository;
import ro.lsacbucuresti.lanpartyquiz.repository.UserPointsRepository;
import ro.lsacbucuresti.lanpartyquiz.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;

/**
 * Created by cristi on 05 - October - 2017
 */
@Service
public class UserPointsService {

    @Autowired
    private UserPointsRepository userPointsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Integer getUserPoints(Long userID){
        final Integer[] points = {0};
        User user = userRepository.findOne(userID);
        userPointsRepository.findByUser(user).forEach(u -> points[0] = points[0] + u.getPoints());
        return points[0];
    }

    public List<UserPointsDTO> getTop10() {
        return userPointsRepository.findAllForTop();
    }


    public void addPoints(String userName, Integer nrOfPoints, Long questionId){
        Question question = questionRepository.findOne(questionId);
        User user = userRepository.findByUsername(userName);
        UserPoints userPoints = new UserPoints();
        userPoints.setPoints(nrOfPoints);
        userPoints.setUser(user);
        userPoints.setQuestion(question);
        try {
            userPointsRepository.save(userPoints);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
