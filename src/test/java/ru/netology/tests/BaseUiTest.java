package ru.netology.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseUiTest {
    @BeforeEach
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }
}
