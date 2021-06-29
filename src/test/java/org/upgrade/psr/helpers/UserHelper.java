package org.upgrade.psr.helpers;

import org.upgrade.psr.helpers.dto.UserRequestDto;

public class UserHelper {

    public static UserRequestDto createUserDto(String firstName, String lastName, String email) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setFirstName(firstName);
        userRequestDto.setLastName(lastName);
        userRequestDto.setEmail(email);
        return userRequestDto;
    }
}
