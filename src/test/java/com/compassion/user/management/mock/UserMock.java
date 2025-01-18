package com.compassion.user.management.mock;

import com.compassion.user.management.domain.User;
import com.compassion.user.management.domain.enums.DocumentType;
import com.compassion.user.management.domain.enums.UserType;
import com.compassion.user.management.domain.vo.UserVO;

public class UserMock {


    public static User userMock() {
        return User.builder()
                .id("e4dc13ac-388c-4bd7-b9b1-8062c3ae1205")
                .neighborhood("Vila Emilio")
                .active(true)
                .email("teste@gmail.com")
                .name("Teste LTDA")
                .phone("11945756561")
                .userType(UserType.ENTREPRENEUR)
                .verified(true)
                .document("45644588867")
                .documentType(DocumentType.CNPJ)
                .description("The Best Company of make you beautiful")
                .build();
    }

    public static UserVO userVOMock() {
        return UserVO.of(userMock());
    }
}
