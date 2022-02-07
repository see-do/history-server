package com.UMC.history.DTO;

import lombok.*;

@NoArgsConstructor
public class UserDTO {
    @Setter
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
