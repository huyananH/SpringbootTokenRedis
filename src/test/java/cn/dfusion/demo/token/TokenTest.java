package cn.dfusion.demo.token;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class TokenTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate<String, String> template;


    @Test
    public void testObj() throws Exception {
        stringRedisTemplate.opsForValue().set("name","huyanan");
        System.out.println("添加成功");
    }
    @Test
    public void delete() {
        template.delete("name");
    }
}
