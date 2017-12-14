package com.sakksoftware.web.bbrestapioauth2.Service;

import com.sakksoftware.web.bbrestapioauth2.model.TokenBlackList;
import com.sakksoftware.web.bbrestapioauth2.repository.TokenBlackListRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TokenBlackListService {

    @Autowired
    TokenBlackListRepository tokenBlackListRepository;

    public Boolean isBlackListed( String jti ) throws TokenNotFoundException {
        Optional<TokenBlackList> token = tokenBlackListRepository.findByJti(jti);
        if ( token.isPresent() ) {
            return token.get().getBlackListed();
        } else {
            throw new TokenNotFoundException(jti);
        }
    }

    @Async
    public void addToEnabledList(String userId, String jti, Long expired ) {
        // clean all black listed tokens for user
        List<TokenBlackList> list = tokenBlackListRepository.queryAllByUserIdAndBlackListedTrue(userId); //.queryAllByUserIdAndIsBlackListedTrue(userId);
        if (list != null && list.size() > 0) {
            list.forEach(
                    token -> {
                        token.setBlackListed(true);
                        tokenBlackListRepository.save(token);
                    }
            );
        }
        // Add new token white listed
        TokenBlackList tokenBlackList = new TokenBlackList(userId, jti, expired);
        tokenBlackList.setBlackListed(false);
        tokenBlackListRepository.save(tokenBlackList);
        tokenBlackListRepository.deleteAllByUserIdAndExpiresBefore(userId, new Date().getTime());
    }

    @Async
    public void addToBlackList(String jti ) throws TokenNotFoundException {
        Optional<TokenBlackList> tokenBlackList = tokenBlackListRepository.findByJti(jti);
        if ( tokenBlackList.isPresent() ) {
            tokenBlackList.get().setBlackListed(true);
            tokenBlackListRepository.save(tokenBlackList.get());
        } else throw new TokenNotFoundException(jti);
    }

    public static class TokenNotFoundException extends Exception {
        public String jti;
        public String message;
        public TokenNotFoundException(String jti) {
            super();
            this.jti = jti;
            message = String.format("Token with jti[%s] not found.",jti);
        }
    }
}