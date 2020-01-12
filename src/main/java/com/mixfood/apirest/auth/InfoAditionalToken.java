package com.mixfood.apirest.auth;

import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAditionalToken implements TokenEnhancer
{
    @Autowired
    private UserService userService;
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String,Object> info = new HashMap<>();
        User user = userService.findByEmail(oAuth2Authentication.getName());
        info.put("info",oAuth2Authentication.getName());
        info.put("email",user.getEmail());
        info.put("username",user.getName()+" "+user.getLastname());
        info.put("id",user.getId());
        info.put("profileImage",user.getPorfileimageRoute());
        info.put("name",user.getName());
        info.put("lastname",user.getLastname());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
