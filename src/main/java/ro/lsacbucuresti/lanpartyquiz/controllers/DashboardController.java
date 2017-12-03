package ro.lsacbucuresti.lanpartyquiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.lsacbucuresti.lanpartyquiz.dto.UserDTO;
import ro.lsacbucuresti.lanpartyquiz.dto.UserDashDTO;
import ro.lsacbucuresti.lanpartyquiz.model.Question;
import ro.lsacbucuresti.lanpartyquiz.service.DashboardService;

import java.util.List;

/**
 * Created by cristi on 28 - October - 2017
 */
@RestController
@RequestMapping("/dashboard")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/users")
    public List<UserDashDTO> getAll(){
        return dashboardService.getUsers();
    }

    @GetMapping("/questions")
    public List<Question> getQuestions(){
        return dashboardService.getAllQuestion();
    }

}
