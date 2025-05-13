package com.hcmute.thuexe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.hcmute.thuexe.dto.request.EditProfileRequest;
import com.hcmute.thuexe.dto.response.UserProfileResponse;
import com.hcmute.thuexe.dto.response.UserSearchResponse;
import com.hcmute.thuexe.exception.ResourceNotFoundException;
import com.hcmute.thuexe.model.Account;
import com.hcmute.thuexe.model.User;
import com.hcmute.thuexe.repository.AccountRepository;
import com.hcmute.thuexe.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private UserRepository userRepo;

    /**
     * Trả về profile cơ bản của người dùng đang login
     */
    public UserProfileResponse getProfile(Authentication authentication) {
        String username = authentication.getName();
    
        Account account = accountRepo.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tài khoản"));
    
        User user = userRepo.findByAccount_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thông tin người dùng"));
    
        return new UserProfileResponse(
            user.getUserId(),
            user.getName(),
            account.getEmail(),
            user.getAddress(),
            user.getPhone(),
            user.getGender(),
            user.getBirthdate() != null ? user.getBirthdate().toString() : null,
            user.getImageUrl(),
            user.getRole(),
            user.getCreatedAt() != null ? user.getCreatedAt().toString() : null
        );
    }    

    /**
     * Tìm user theo từ khóa (name hoặc email), không bao gồm bản thân
     */
    public List<UserSearchResponse> searchUsers(String keyword, Authentication authentication) {
        String username = authentication.getName();
        User currentUser = userRepo.findByAccount_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException("Người dùng không tồn tại"));

        List<User> matchedUsers = userRepo.searchUsersByKeyword(keyword, currentUser.getUserId());

        return matchedUsers.stream()
                .map(u -> new UserSearchResponse(
                        u.getUserId(),
                        u.getName(),
                        u.getAccount().getEmail(),
                        u.getImageUrl()
                ))
                .collect(Collectors.toList());
    }
    public void editProfile(EditProfileRequest request, Authentication authentication) {
        String username = authentication.getName();

        User user = userRepo.findByAccount_Username(username)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng"));

        if (request.getName() != null && !request.getName().isEmpty()) {
            user.setName(request.getName());
        }

        if (request.getPhone() != null && !request.getPhone().isEmpty()) {
            user.setPhone(request.getPhone());
        }

        if (request.getBirthdate() != null) {
            user.setBirthdate(request.getBirthdate());
        }

        if (request.getGender() != null && !request.getGender().isEmpty()) {
            user.setGender(request.getGender());
        }

        userRepo.save(user);
    }

    public UserProfileResponse getUserById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng"));
        
        return new UserProfileResponse(
            user.getUserId(),
            user.getName(),
            user.getAccount().getEmail(),
            user.getAddress(),
            user.getPhone(),
            user.getGender(),
            user.getBirthdate() != null ? user.getBirthdate().toString() : null,
            user.getImageUrl(),
            user.getRole(),
            user.getCreatedAt() != null ? user.getCreatedAt().toString() : null
        );
    }
}
