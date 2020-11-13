package kr.co.kcamp.web;

import kr.co.kcamp.domain.DealerUserRepository;
import kr.co.kcamp.service.DealerUserLoginService;
import kr.co.kcamp.service.cars.S3Service;
import kr.co.kcamp.web.dto.DealerUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class DealerUserApiController {

    private final S3Service s3Service;
    private final DealerUserLoginService dealerUserLoginService;
    private final DealerUserRepository dealerUserRepository;

    @PostMapping("/api/v2/dealer")
    @ResponseBody
    public String dealsave(DealerUserDto dealerUserDto) throws IOException {

        dealerUserLoginService.savePost(dealerUserDto);

        return "/";
    }

    @PostMapping("/api/v2/dealer/update/{id}")
    public Long dealupdate(@PathVariable Long id, @ModelAttribute DealerUserDto dealerUserDto) throws IOException {
        return dealerUserLoginService.update(id, dealerUserDto);
    }

    @DeleteMapping("/api/v2/dealer/delete/{id}")
    public Long dealdelete(@PathVariable Long id) {
        dealerUserLoginService.delete(id);
        return id;
    }

    @PostMapping("/api/v2/dealer/login")
    @ResponseBody
    public DealerUserDto signIn(@RequestBody DealerUserDto dealerUserDto) {
        dealerUserLoginService.signIn(dealerUserDto);

        return dealerUserDto;
    }


}
