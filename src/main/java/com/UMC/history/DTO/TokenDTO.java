package com.UMC.history.DTO;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDTO {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}
