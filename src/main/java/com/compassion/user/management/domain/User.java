package com.compassion.user.management.domain;

import com.compassion.user.management.domain.enums.DocumentType;
import com.compassion.user.management.domain.enums.UserType;
import com.compassion.user.management.domain.vo.UserVO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@ToString
@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String document;
    private DocumentType documentType;
    private UserType userType;
    private String description;
    private String neighborhood;
    private Boolean verified;
    private Boolean active;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private List<String> feedbackIds;

    public static User of(final UserVO userVO) {
        return User.builder()
                .id(UUID.randomUUID().toString())
                .name(userVO.name())
                .email(userVO.email())
                .phone(userVO.phone())
                .document(userVO.document())
                .documentType(userVO.documentType())
                .userType(userVO.userType())
                .description(userVO.description())
                .neighborhood(userVO.neighborhood())
                .verified(false)
                .active(false)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
