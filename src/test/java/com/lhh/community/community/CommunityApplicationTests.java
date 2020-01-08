package com.lhh.community.community;

import com.lhh.community.services.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {


    @Autowired
    private QuestionService questionService;


    @Test
    public void testCount()
    {
        int a = questionService.count();
        System.out.println(a);
    }

    @Test
    void contextLoads() {
    }

}
