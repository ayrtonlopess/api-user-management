package com.compassion.user.management.domain.vo;

import com.compassion.user.management.domain.User;
import com.compassion.user.management.domain.enums.DocumentType;
import com.compassion.user.management.domain.enums.UserType;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public record UserVO(
        String id,
        @NotEmpty(message = "Name cannot be empty.")
        String name,
        @NotEmpty(message = "Email cannot be empty.")
        String email,
        String phone,
        @NotEmpty(message = "Document cannot be empty.")
        String document,
        DocumentType documentType,
        UserType userType,
        String description,
        String neighborhood,
        Boolean verified,
        Boolean active
) {

    public static UserVO of(final User user) {
        return new UserVO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getDocument(),
                user.getDocumentType(),
                user.getUserType(),
                user.getDescription(),
                user.getNeighborhood(),
                user.getVerified(),
                user.getActive()
        );
    }

    public static List<UserVO> of(final List<User> userList) {
        List<UserVO> listUserVO = new ArrayList<>();
        userList.forEach(user -> {
            var userVO = new UserVO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getDocument(),
                    user.getDocumentType(),
                    user.getUserType(),
                    user.getDescription(),
                    user.getNeighborhood(),
                    user.getVerified(),
                    user.getActive()
            );

            listUserVO.add(userVO);
        });

        return listUserVO;
    }
}
