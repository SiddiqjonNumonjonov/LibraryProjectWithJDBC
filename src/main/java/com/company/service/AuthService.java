package com.company.service;

import com.company.container.ComponentContainer;
import com.company.dto.BookDTO;
import com.company.dto.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.util.MD5Util;
import com.company.util.ValidationUtil;

import java.time.LocalDateTime;

public class AuthService {
    public void login(String login, String password) {
        if(login == null || login.isBlank() || login.trim().length() < 3
        || password == null || password.isBlank() || password.trim().length() < 5) {
            return;
        }
        ProfileDTO dto = ComponentContainer.profileRepository.getByLogin(login);
        if(dto == null) {
            return;
        }
        ComponentContainer.currentProfileId = dto.getId();
        if(!MD5Util.encode(password).equals(dto.getPassword())) {
            return;
        }
        if(dto.getRole().equals(ProfileRole.ADMIN)) {
            ComponentContainer.adminController.start();
        } else if (dto.getRole().equals(ProfileRole.STUDENT)) {
            ComponentContainer.studentController.start();
        } else if (dto.getRole().equals(ProfileRole.STUFF)) {
            //stuff
        }
    }

    public Boolean registration(ProfileDTO profileDTO) {
        if(!ValidationUtil.checkProfile(profileDTO)) {
            return  false;
        }
        ProfileDTO profile = ComponentContainer.profileRepository.getByLogin(profileDTO.getLogin());
        if(profile != null) {
            return  false;
        }
        profileDTO.setPassword(MD5Util.encode(profileDTO.getPassword()));
        profileDTO.setRole(ProfileRole.STUDENT);
        profileDTO.setStatus(ProfileStatus.ACTIVE);
        profileDTO.setCreatedAt(LocalDateTime.now());
        return ComponentContainer.profileRepository.create(profileDTO);
    }
}
