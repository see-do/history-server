package com.UMC.history.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDTO {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class User{
        private String id;
        private String nickName;
        private String password;

        public User(){}
    }
}
