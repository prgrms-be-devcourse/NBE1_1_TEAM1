package com.programmers.mycoffee.controller.api.admin;

import com.programmers.mycoffee.model.admin.JoinDto;
import com.programmers.mycoffee.service.admin.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class JoinController {
    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinDto joinDto) {
        try {
            JoinDto processedJoinDto = joinService.joinProcess(joinDto);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(joinDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body("입력 오류: " + e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("사용자 중복: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류: " + e.getMessage());
        }
    }
}
