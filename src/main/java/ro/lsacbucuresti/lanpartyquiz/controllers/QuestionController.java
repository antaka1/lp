package ro.lsacbucuresti.lanpartyquiz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ro.lsacbucuresti.lanpartyquiz.dto.AddQuestionDTO;
import ro.lsacbucuresti.lanpartyquiz.dto.QuestionDTO;
import ro.lsacbucuresti.lanpartyquiz.model.Question;
import ro.lsacbucuresti.lanpartyquiz.service.QuestionService;
import ro.lsacbucuresti.lanpartyquiz.service.QuestionSubmitService;

import java.util.Date;
import java.util.Map;

/**
 * Created by cristi on 05 - October - 2017
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionSubmitService questionSubmitService;

    @GetMapping("/getById/{id}")
    public QuestionDTO getById(@PathVariable("id") Long id){
        return questionService.findOne(id);
    }

    @GetMapping(value = "/getNow", produces = "application/json")
    public QuestionDTO getTodayQuestion(){
        return questionService.findByDate(new Date());
    }

    @PostMapping(value = "/submitAnswer", consumes = "application/json")
    public String submitAnswer(@RequestBody Map<String,String> input){
        Long questionId = Long.valueOf(input.get("questionId"));
        String answer = input.get("answer");
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return questionService.submitAnswer(questionId,answer,username);
    }

    @PostMapping(value = "/startAnswer")
    public boolean startsAnswer(@RequestBody Map<String,String> input){
        Long qustionId = Long.valueOf(input.get("questionId"));
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return questionSubmitService.userStartsAnswering(username,qustionId);
    }

    @PostMapping(value ="/expired")
    public void timeExpired(@RequestBody Map<String,String> input){
        Long questionId = Long.valueOf(input.get("questionId"));
        String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        questionService.timeExpired(questionId,username);
    }

    @PostMapping(value = "/addQuestion")
    public String addQuestion(@RequestBody AddQuestionDTO questionDTO){
        return questionService.addQuestion(questionDTO);
    }
}
