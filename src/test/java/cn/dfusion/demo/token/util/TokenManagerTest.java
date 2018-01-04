package cn.dfusion.demo.token.util;

import cn.dfusion.demo.token.entity.TokenModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class TokenManagerTest {

    @Autowired
    private TokenManager tokenManager;

    @Test
    public void get () {
        TokenModel tokenModel = tokenManager.createToke(Long.parseLong("1"));

    }
}
