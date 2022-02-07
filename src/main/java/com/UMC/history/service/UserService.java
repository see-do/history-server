package com.UMC.history.service;

import com.UMC.history.DTO.TokenDTO;
import com.UMC.history.DTO.UserDTO;
import com.UMC.history.entity.strongEntity.UserEntity;
import com.UMC.history.entity.weekEntity.RefreshToken;
import com.UMC.history.jwt.TokenProvider;
import com.UMC.history.repository.RefreshTokenRepository;
import com.UMC.history.repository.UserRepository;
import com.UMC.history.util.Authority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    private TokenProvider tokenProvider;
    private RefreshTokenRepository refreshTokenRepository;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,AuthenticationManagerBuilder authenticationManagerBuilder,TokenProvider tokenProvider,RefreshTokenRepository refreshTokenRepository){
        this.userRepository = userRepository;

        this.passwordEncoder=passwordEncoder;
        this.authenticationManagerBuilder =authenticationManagerBuilder;
        this.tokenProvider =tokenProvider;
        this.refreshTokenRepository =refreshTokenRepository;
    }


    public void saveUserData(@RequestBody UserDTO.User user){
        //encode 는 패스워드를 암호화 할 때 사용
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword); // password를 다시 setting하기 위해 UserEntity에 @Setter추가
        UserEntity userEntity = UserEntity.builder()
                .userId(user.getId())
                .nickName(user.getNickName())
                .password(user.getPassword())
                .authority(Authority.ROLE_USER)
                .build();
        userRepository.save(userEntity);
    }

    public boolean nickNameExist(@RequestBody UserDTO.User user) {
        return userRepository.existsByNickName(user.getNickName());
    }

    public Boolean changeNickName(UserDTO.User user) {
        UserEntity hasUserId = userRepository.findByUserId(user.getId());// jwt
        if(hasUserId!=null){
            hasUserId.changeNickName(user.getNickName());
            userRepository.save(hasUserId);
            return true;
        }else return false;
    }

    public TokenDTO login(UserDTO.User user) {
        UserEntity findUser = userRepository.findByUserId(user.getId());
        if(findUser == null){
            return new TokenDTO();
        }

        if(!passwordEncoder.matches(user.getPassword(), findUser.getPassword())){ // 그냥 받아온 password를 넣으면 알아서 암호화해서 비교함.
            return new TokenDTO();
        }
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDTO tokenDto = tokenProvider.generateTokenDto(authentication);
        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        // 5. 토큰 발급
        return tokenDto;
    }

    public boolean checkUserId(String userId){
        return userRepository.existsByUserId(userId);
    }


    public TokenDTO reissue(TokenDTO tokenRequestDto) { //재발급
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKeyId(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDTO tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

}
