package cn.dfusion.demo.token.util.impl;

import cn.dfusion.demo.token.entity.TokenModel;
import cn.dfusion.demo.token.util.TokenManager;
import cn.dfusion.demo.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class TokenManagerImpl implements TokenManager{

    private RedisTemplate<Long, String> redis;

    @Autowired
    public void setRedis(@Qualifier("redisTemplate") RedisTemplate redis) {
        this.redis = redis;
        //泛型设置成Long后必须更改对应的序列化方案
        redis.setKeySerializer(new JdkSerializationRedisSerializer());
    }


    //创建Token
    @Override
    public TokenModel createToke(long userId) {
        String token = UUID.randomUUID().toString().replace("-","");
        TokenModel model = new TokenModel(userId, token);
        //存入数据库
        redis.boundValueOps(userId).set(token, Constant.TOKEN_EXPIRES_MINS, TimeUnit.MINUTES);
        return model;
    }

    //检测Token是否有效
    @Override
    public boolean checkToken(TokenModel model) {
        //如果model为空
        if (null == model) {
            return false;
        }
        String token = redis.boundValueOps(model.getUserId()).get();
        //如果Token为空，或者Token和传回来的不同
        if (null == token || !token.equals(model.getToken())) {
            return false;
        }
        //如果有，则用户进行一次有效操作，延长时间

        redis.boundValueOps(model.getUserId()).expire(Constant.TOKEN_EXPIRES_HOURS, TimeUnit.MINUTES);
        return true;
    }

    //从字符串中解析Token
    @Override
    public TokenModel getToken(String authentication) {
        if (null == authentication || authentication.length() == 0) {
            return null;
        }
        //解析authentication
        String[] param = authentication.split("_");
        if (param.length !=2) {
            return null;
        }
        Long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new TokenModel(userId,token);
    }

    @Override
    public void deleteToke(long userId) {
        redis.delete(userId);
    }
}
