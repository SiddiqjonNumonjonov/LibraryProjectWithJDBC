package com.company.service;

import com.company.container.ComponentContainer;
import com.company.dto.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.util.MD5Util;

import java.time.LocalDateTime;

public class InitService {
    public void init() {
        ProfileDTO profileDTO = ComponentContainer.profileRepository.getByLogin("admin");
        if(profileDTO == null) {
            ProfileDTO admin = new ProfileDTO();
            admin.setLogin("admin");
            admin.setPhoneNumber("+998887122829");
            admin.setPassword(MD5Util.encode("12345"));
            admin.setStatus(ProfileStatus.ACTIVE);
            admin.setRole(ProfileRole.ADMIN);
            admin.setName("Siddiqjon");
            admin.setSurname("Numonjonov");
            admin.setCreatedAt(LocalDateTime.now());
            ComponentContainer.profileRepository.create(admin);
        }
    }
}
