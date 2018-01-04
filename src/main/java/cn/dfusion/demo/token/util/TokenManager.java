package cn.dfusion.demo.token.util;

import cn.dfusion.demo.token.entity.TokenModel;

public interface TokenManager {

    public static  final String SERVER_NAME = "cn.dfusion.utils.impl.TokenManagerImpl";
    /**
     * 创建一个Token，关联指定用户
     * @param userId 用户ID
     * @return token 生成的Token
     */
    TokenModel createToke(long userId);
    /**
     * 检测Token是否有效
     * @param model Token
     * @return 是否有效
     */
    boolean checkToken(TokenModel model);
    /**
     * 在字符串中解析Token
     * @param authentication 加密后的Token字符串
     * @return TokenModel
     */
    TokenModel getToken(String authentication);
    /**
     * 清除指定Token
     * @param userId 用户ID
     */
    void deleteToke(long userId);
}
