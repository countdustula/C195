package com.example.c195;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class loginControllerTest {

    @Test
    public void switchToMainScreen() throws IOException {
        loginController loginController = new loginController();
        assertEquals(true, loginController.doesExistInDB("test", "test"));
        assertEquals(true, loginController.doesExistInDB("admin", "admin"));
        assertEquals(false, loginController.doesExistInDB("aa", "adma"));
        assertEquals(false, loginController.doesExistInDB("admin", "test"));
        assertEquals(false, loginController.doesExistInDB("test", "admin"));
    }
}