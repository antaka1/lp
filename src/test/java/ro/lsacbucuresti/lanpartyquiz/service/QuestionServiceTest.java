package ro.lsacbucuresti.lanpartyquiz.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.lsacbucuresti.lanpartyquiz.model.Question;
import ro.lsacbucuresti.lanpartyquiz.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by cristi on 07 - October - 2017
 */
public class QuestionServiceTest {

    @InjectMocks
    private QuestionService questionService;

    @Mock
    private QuestionRepository questionRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findOne() throws Exception {
        Question question = new Question();
        question.setId(10L);
        when(questionRepository.findOne(anyLong())).thenReturn(question);
        assertNotNull(questionService.findOne(10L));
    }

    @Test
    public void findByDate() throws Exception {
        List<Question> list = new ArrayList();
        Question question = new Question();
        question.setDate(new Date());
        list.add(question);
        when(questionRepository.findByDate(any())).thenReturn(list);
        assertNotNull(questionService.findByDate(new Date()));
    }

}