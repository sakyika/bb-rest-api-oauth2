package com.sakksoftware.web.bbrestapioauth2.repository;

import com.sakksoftware.web.bbrestapioauth2.model.TokenBlackList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TokenBlackListRepository  extends MongoRepository<TokenBlackList, String> {
    Optional<TokenBlackList> findByJti(String jti);
    List<TokenBlackList> queryAllByUserIdAndBlackListedTrue(String userId);
    TokenBlackList save(TokenBlackList tokenBlackList);
    List<TokenBlackList> deleteAllByUserIdAndExpiresBefore(String userId, Long date);
}
