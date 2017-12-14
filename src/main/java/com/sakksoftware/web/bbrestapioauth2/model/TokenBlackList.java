package com.sakksoftware.web.bbrestapioauth2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class TokenBlackList {

    @Id
    private String jti;
    private String userId;
    private Long expires;
    private Boolean blackListed;

    public TokenBlackList() {}

    public TokenBlackList(String userId, String jti, Long expires) {
        this.userId = userId;
        this.jti = jti;
        this.expires = expires;
    }
/*
    public boolean isBlackListed() {
        return getBlackListed();
    }
*/
}
