package ro.lsacbucuresti.lanpartyquiz.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by cristi on 07 - October - 2017
 */
public class MainControllerTest {

    @InjectMocks
    private MainController mainController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void sayHello() throws Exception {
        assertEquals(mainController.sayHello(),"dude");
    }

}