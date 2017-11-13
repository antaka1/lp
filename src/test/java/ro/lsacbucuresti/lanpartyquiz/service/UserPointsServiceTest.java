package ro.lsacbucuresti.lanpartyquiz.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.lsacbucuresti.lanpartyquiz.model.User;
import ro.lsacbucuresti.lanpartyquiz.model.UserPoints;
import ro.lsacbucuresti.lanpartyquiz.repository.UserPointsRepository;
import ro.lsacbucuresti.lanpartyquiz.repository.UserRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Created by cristi on 07 - October - 2017
 */
public class UserPointsServiceTest {

    @InjectMocks
    private UserPointsService userPointsService;
    @Mock
    private UserPointsRepository userPointsRepository;
    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserPoints() throws Exception {
        UserPoints userPoints = new UserPoints();
        UserPoints userPoints1 = new UserPoints();
        User user = new User();
        user.setId(1L);
        userPoints.setUser(user);
        userPoints.setPoints(2);
        userPoints1.setUser(user);
        userPoints1.setPoints(3);
        when(userRepository.findOne(anyLong())).thenReturn(user);
        when(userPointsRepository.findByUser(any())).thenReturn(Arrays.asList(userPoints,userPoints1));
        assertEquals(userPointsService.getUserPoints(1L).compareTo(5) ,0);
    }

    @Test
    public void getTop10() throws Exception {
    }

}