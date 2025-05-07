package com.hcmute.thuexe.controller;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.hcmute.thuexe.dto.request.BookingRequest;
import com.hcmute.thuexe.dto.request.BookingUpdateRequest;
import com.hcmute.thuexe.dto.request.CancelBookingRequest;
import com.hcmute.thuexe.dto.request.MessageRequest;
import com.hcmute.thuexe.dto.request.SearchCarRequest;
import com.hcmute.thuexe.dto.response.*;
import com.hcmute.thuexe.model.Car;
import com.hcmute.thuexe.model.User;
import com.hcmute.thuexe.payload.ApiResponse;
import com.hcmute.thuexe.repository.UserRepository;
import com.hcmute.thuexe.service.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * API: GET /api/user/profile
     * Lấy thông tin người dùng hiện tại (username từ token)
     */
    @GetMapping("/profile")
    public UserProfileResponse getProfile(Authentication authentication) {
        return userService.getProfile(authentication);
    }

    /**
     * API: GET /api/user/conversations
     * Lấy tất cả đoạn hội thoại của user đang login
     */
    @GetMapping("/conversations")
    public List<ConversationResponse> getAllConversations(Authentication authentication) {
        return conversationService.getAllConversationsForUser(authentication);
    }

    /**
     * API: POST /api/user/message
     * Gửi tin nhắn mới (TEXT hoặc POST)
     */
    @PostMapping("/message")
    public MessageResponse sendMessage(@RequestBody MessageRequest request, Authentication authentication) {
        MessageResponse response = messageService.sendMessage(request, authentication);
        messagingTemplate.convertAndSend("/topic/conversations/" + response.getConversationId(), response);
        return response;
    }

    /**
     * API: GET /api/user/messages/{conversationId}
     * Lấy danh sách tin nhắn trong một đoạn hội thoại
     */
    @GetMapping("/messages/{conversationId}")
    public List<MessageResponse> getMessagesByConversation(
            @PathVariable Long conversationId,
            Authentication authentication) {
        return messageService.getMessagesByConversation(conversationId, authentication);
    }

    /**
     * API: GET /api/user/search?keyword=...
     * Tìm kiếm người dùng theo tên hoặc email (loại trừ chính mình)
     */
    @GetMapping("/search")
    public List<UserSearchResponse> searchUsers(String keyword, Authentication authentication) {
        return userService.searchUsers(keyword, authentication);
    }

    /**
     * API: POST /api/user/cars/list
     * Tìm kiếm xe theo điều kiện
     */
    @PostMapping("/cars/list")
    public ApiResponse<List<CarListResponse>> searchCars(@RequestBody SearchCarRequest request, Authentication authentication) {
        List<Car> cars = carService.searchCars(request);
        if (cars.isEmpty()) {
            return new ApiResponse<>(false, "Không tìm thấy xe nào");
        }

        List<CarListResponse> response = cars.stream()
            .map(car -> {
                Double avgRating = carService.getAvgRating(car.getCarId());
                Long tripCount = carService.getTripCount(car.getCarId());
                return new CarListResponse(car, avgRating != null ? avgRating : 0.0, tripCount != null ? tripCount : 0L);
            })
            .toList();

        return new ApiResponse<>(true, "Tìm kiếm thành công", response);
    }

    /**
     * API: GET /api/user/car/{id}
     * Lấy chi tiết xe
     */
    @GetMapping("/car/{id}")
    public ResponseEntity<CarDetailResponse> getCarDetail(@PathVariable Long id) {
        return ResponseEntity.ok(carService.getCarDetail(id));
    }

    /**
     * API: POST /api/user/booking/preview
     * Xem trước thông tin đặt xe
     */
    @PostMapping("/booking/preview")
    public ResponseEntity<BookingPreviewResponse> preview(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.previewBooking(request));
    }

    /**
     * API: POST /api/user/booking/cancel
     * Hủy đơn đặt xe
     */
    @PostMapping("/booking/cancel")
    public ResponseEntity<?> cancelBooking(@RequestBody CancelBookingRequest request, Authentication authentication) {
        try {
            if (authentication == null || authentication.getPrincipal() == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ApiResponse<>(false, "Chưa xác thực người dùng", null));
            }

            String username = authentication.getPrincipal().toString();
            User user = userRepository.findByAccount_Username(username)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

            bookingService.cancelBooking(request, user.getUserId());
            return ResponseEntity.ok(new ApiResponse<>(true, "Huỷ đơn thành công", null));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Đã xảy ra lỗi không xác định", null));
        }
    }

    @Autowired
    private UserRepository userRepository;
    /**
     * API: POST /api/user/booking/confirm
     * Xác nhận đặt xe
     */
    @PostMapping("/booking/confirm")
    public ResponseEntity<?> confirmBooking(
            @RequestBody BookingRequest request,
            Authentication authentication) {

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Chưa xác thực người dùng", null));
        }

        String username = authentication.getPrincipal().toString();
        try {
            User user = userRepository.findByAccount_Username(username)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
                    
            String response = String.valueOf(bookingService.confirmBooking(request, user.getUserId()));
            return ResponseEntity.ok(new ApiResponse<>(true, "Đặt xe thành công", response));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Đã xảy ra lỗi không xác định", null));
        }
    }

    /**
     * API: GET /api/user/booking/{bookingId}
     * Lấy thông tin chi tiết của đơn đặt xe
     */
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<?> getBookingDetail(
            @PathVariable Long bookingId,
            Authentication authentication) {

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Chưa xác thực người dùng", null));
        }

        String username = authentication.getPrincipal().toString();
        try {
            User user = userRepository.findByAccount_Username(username)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

            BookingDetailResponse response = bookingService.getBookingDetail(bookingId, user.getUserId());
            return ResponseEntity.ok(new ApiResponse<>(true, "Lấy thông tin booking thành công", response));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Đã xảy ra lỗi không xác định", null));
        }
    }

    /**
     * API: PUT /api/user/booking/update
     * Cập nhật thông tin booking
     */
    @PutMapping("/booking/update")
    public ResponseEntity<?> updateBooking(
            @RequestBody BookingUpdateRequest request,
            Authentication authentication) {

        if (authentication == null || authentication.getPrincipal() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Chưa xác thực người dùng", null));
        }

        String username = authentication.getPrincipal().toString();
        User user = userRepository.findByAccount_Username(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

        try {
            String response = bookingService.updateBooking(request, user.getUserId());
            return ResponseEntity.ok(new ApiResponse<>(true, "Cập nhật thành công", response));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "Lỗi khi cập nhật booking", null));
        }
    }


}
