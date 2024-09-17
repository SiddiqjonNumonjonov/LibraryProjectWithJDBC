package com.company.service;

import com.company.container.ComponentContainer;
import com.company.dto.ProfileDTO;
import com.company.dto.ProfileForResultDTO;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.util.MD5Util;
import com.company.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.List;

public class ProfileService {
    public Boolean add(ProfileDTO profileDTO) {
        if(!ValidationUtil.checkProfile(profileDTO)) {
            return  null;
        }
        ProfileDTO profile = ComponentContainer.profileRepository.getByLogin(profileDTO.getLogin());
        if(profile != null) {
            return  null;
        }
        profileDTO.setPassword(MD5Util.encode(profileDTO.getPassword()));
        profileDTO.setStatus(ProfileStatus.ACTIVE);
        profileDTO.setCreatedAt(LocalDateTime.now());
        return ComponentContainer.profileRepository.create(profileDTO);
    }

    public List<ProfileForResultDTO> allProfiles() {
        return ComponentContainer.profileRepository.getAllProfiles(ProfileRole.ADMIN,ProfileRole.STUFF);
    }

    public List<ProfileForResultDTO> search(String query) {
       return ComponentContainer.profileRepository.search(query);
    }

    public Boolean changeStatus(Integer id) {
        ProfileDTO profileDTO = ComponentContainer.profileRepository.getById(id);
        if(profileDTO == null) {
            return  false;
        }
        if(profileDTO.getStatus().equals(ProfileStatus.ACTIVE)) {
            return ComponentContainer.profileRepository.changeStatus(id,ProfileStatus.BLOCKED);
        } else if (profileDTO.getStatus().equals(ProfileStatus.BLOCKED)) {
            return ComponentContainer.profileRepository.changeStatus(id,ProfileStatus.ACTIVE);
        }
        return false;
    }

    public List<ProfileForResultDTO> getAllStudents(ProfileRole profileRole) {
        return ComponentContainer.profileRepository.getAllProfiles(profileRole);
    }

    public List<ProfileForResultDTO> searchStudent(String value) {
        return ComponentContainer.profileRepository.searchStudent(value);
    }
}
