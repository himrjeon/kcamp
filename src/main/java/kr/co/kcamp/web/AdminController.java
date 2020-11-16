package kr.co.kcamp.web;

import kr.co.kcamp.config.auth.LoginUser;
import kr.co.kcamp.config.auth.dto.SessionUser;
import kr.co.kcamp.domain.DealerUser;
import kr.co.kcamp.domain.UsedCars;
import kr.co.kcamp.service.cars.*;
import kr.co.kcamp.service.popup.PopUpS3UploadService;
import kr.co.kcamp.service.posts.*;
import kr.co.kcamp.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminController {

    private final PopUpS3UploadService popUpS3UploadService;
    private final NoticeService noticeService;
    private final NewsService newsServices;
    private final HttpSession httpSession;
    private final ImportCarsS3UploadService importCarsS3UploadService;
    private final UsedCarsS3UploadService usedCarsS3UploadService;
    private final S3Service s3Service;
    private final GuestBookService guestBookService;
    private final CarGuestBookService carGuestBookService;
    private final DirectCarsS3UploadService directCarsS3UploadService;
    private final ShowRoomS3UploadService showRoomS3UploadService;
    private final PostsService postsService;
    private final BookCarService bookCarService;

    @GetMapping("/admin")
    public String admin(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "admin";
    }

    @GetMapping("/admin/schedule")
    public String schedule(Model model, @LoginUser SessionUser user, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // model.addAttribute("news", newsServices.findAllDesc());

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }
        model.addAttribute("schedulelist", bookCarService.findAllDesc());


        return "admin-schedule";
    }

    @GetMapping("/admin/schedule/detail")
    public String scheduleDetail(Model model, @LoginUser SessionUser user, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // model.addAttribute("news", newsServices.findAllDesc());

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("schedulelist", bookCarService.getBoardList(pageable));

        return "admin-schedule-detail";
    }

    @GetMapping("/admin/schedule/update/{id}")
    public String scheduleUpdate(Model model, @LoginUser SessionUser user, @PathVariable Long id) {
        // model.addAttribute("news", newsServices.findAllDesc());

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }
        model.addAttribute("schedulelist", bookCarService.findById(id));

        return "admin-schedule-update";
    }

    @GetMapping("/admin/schedulePopup")
    public String schedulePopup(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        return "schedulePopup";
    }


    @GetMapping("/admin/posts")
    public String postsboard(Model model, @LoginUser SessionUser user, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // model.addAttribute("news", newsServices.findAllDesc());

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("posts", postsService.getBoardList(pageable));

        return "admin-posts";
    }

    @GetMapping("/admin/contactus")
    public String adminGuestbook(Model model, @LoginUser SessionUser user) {
        model.addAttribute("guestbook", guestBookService.findAllDesc());

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "admin-contactus";
    }

    @GetMapping("/admin/contactus/detail/{id}")
    public String adminGuestBookDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {

        GuestBookResponseDto dto = guestBookService.findById(id);
        model.addAttribute("guestbook",dto);

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "admin-contactus-detail";
    }


    @GetMapping("/admin/popup")
    public String adminpopup(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        List<PopupDto> popupDtoList = popUpS3UploadService.getList();
        model.addAttribute("galleryList", popupDtoList);

        return "admin-popup";
    }

    @GetMapping("/admin/popup/save")
    public String adminpopSave(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }
        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "admin-popup-save";
    }

    @GetMapping("/admin/popup/update/{id}")
    public String popupDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PopupDto dto = popUpS3UploadService.findById(id);
        model.addAttribute("popup",dto);

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "admin-popup-update";
    }

    @GetMapping("/event/{id}")
    public String eventpop(@PathVariable Long id, Model model) {
        PopupDto dto = popUpS3UploadService.findById(id);
        model.addAttribute("popup",dto);

        return "event_01";
    }

    @GetMapping("/admin/gallery")
    public String directCar(Model model, @LoginUser SessionUser user, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        //List<DirectCarsDto> directCarsDtoList = directCarsS3UploadService.getList();
        //model.addAttribute("galleryList", directCarsDtoList);

        List<DirectCarsDto> directCarsDtoList = directCarsS3UploadService.getBoardList(pageable);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("galleryList", directCarsDtoList);

        return "admin-directcar";
    }

    @GetMapping("/admin/gallery/save")
    public String directCarSave(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "admin-directcar-save";
    }

    @GetMapping("/admin/gallery/detail/{id}")
    public String directCarsDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        DirectCarsDto dto = directCarsS3UploadService.findById(id);
        model.addAttribute("directcar",dto);

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "admin-directcar-detail";
    }

    @PostMapping("/directcar/upload")
    @ResponseBody
    public String directCarUpload(DirectCarsDto directCarsDto, MultipartFile file, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, MultipartFile file6, MultipartFile file7, MultipartFile file8, MultipartFile file9, MultipartFile file10, MultipartFile file11, MultipartFile file12, MultipartFile file13, MultipartFile file14, MultipartFile file15, MultipartFile file16, MultipartFile file17, MultipartFile file18, MultipartFile file19, MultipartFile file20) throws IOException {
        String imgPath = s3Service.upload(directCarsDto.getFilePath(), file);
        directCarsDto.setFilePath(imgPath);
        String imgPath2 = s3Service.upload(directCarsDto.getFilePath2(), file2);
        directCarsDto.setFilePath2(imgPath2);
        String imgPath3 = s3Service.upload(directCarsDto.getFilePath3(), file3);
        directCarsDto.setFilePath3(imgPath3);
        String imgPath4 = s3Service.upload(directCarsDto.getFilePath4(), file4);
        directCarsDto.setFilePath4(imgPath4);
        String imgPath5 = s3Service.upload(directCarsDto.getFilePath5(), file5);
        directCarsDto.setFilePath5(imgPath5);
        String imgPath6 = s3Service.upload(directCarsDto.getFilePath6(), file6);
        directCarsDto.setFilePath6(imgPath6);
        String imgPath7 = s3Service.upload(directCarsDto.getFilePath7(), file7);
        directCarsDto.setFilePath7(imgPath7);
        String imgPath8 = s3Service.upload(directCarsDto.getFilePath8(), file8);
        directCarsDto.setFilePath8(imgPath8);
        String imgPath9 = s3Service.upload(directCarsDto.getFilePath9(), file9);
        directCarsDto.setFilePath9(imgPath9);
        String imgPath10 = s3Service.upload(directCarsDto.getFilePath10(), file10);
        directCarsDto.setFilePath10(imgPath10);
        String imgPath11 = s3Service.upload(directCarsDto.getFilePath11(), file11);
        directCarsDto.setFilePath11(imgPath11);
        String imgPath12 = s3Service.upload(directCarsDto.getFilePath12(), file12);
        directCarsDto.setFilePath12(imgPath12);
        String imgPath13 = s3Service.upload(directCarsDto.getFilePath13(), file13);
        directCarsDto.setFilePath13(imgPath13);
        String imgPath14 = s3Service.upload(directCarsDto.getFilePath14(), file14);
        directCarsDto.setFilePath14(imgPath14);
        String imgPath15 = s3Service.upload(directCarsDto.getFilePath15(), file15);
        directCarsDto.setFilePath15(imgPath15);
        String imgPath16 = s3Service.upload(directCarsDto.getFilePath16(), file16);
        directCarsDto.setFilePath16(imgPath16);
        String imgPath17 = s3Service.upload(directCarsDto.getFilePath17(), file17);
        directCarsDto.setFilePath17(imgPath17);
        String imgPath18 = s3Service.upload(directCarsDto.getFilePath18(), file18);
        directCarsDto.setFilePath18(imgPath18);
        String imgPath19 = s3Service.upload(directCarsDto.getFilePath19(), file19);
        directCarsDto.setFilePath19(imgPath19);
        String imgPath20 = s3Service.upload(directCarsDto.getFilePath20(), file20);
        directCarsDto.setFilePath20(imgPath20);
        directCarsS3UploadService.savePost(directCarsDto);

        return "admin-directcar";
    }

    @GetMapping("/admin/directcar/update/{id}")
    public String directCarsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        DirectCarsDto dto = directCarsS3UploadService.findById(id);
        model.addAttribute("directcar",dto);

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "admin-directcar-update";
    }

    @GetMapping("/admin/directcar/copy/{id}")
    public String directCarsCopy(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        DirectCarsDto dto = directCarsS3UploadService.findById(id);
        model.addAttribute("directcar",dto);

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "admin-directcar-copy";
    }

    @GetMapping("/admin/review")
    public String notice(Model model, @LoginUser SessionUser user,  @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // model.addAttribute("notice", noticeService.findAllDesc());
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }


        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("notice", noticeService.getBoardList(pageable));

        return "admin-notice";
    }

    @GetMapping("/admin/notice")
    public String newsboard(Model model, @LoginUser SessionUser user, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        // model.addAttribute("news", newsServices.findAllDesc());

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("news", newsServices.getBoardList(pageable));

        return "admin-news";
    }

    @GetMapping("/admin/portfolio")
    public String portfolio(Model model, @LoginUser SessionUser user, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        // List<ShowRoomDto> showRoomDtoList = showRoomS3UploadService.getList();
        // model.addAttribute("galleryList", showRoomDtoList);

        List<ShowRoomDto> showRoomDtoList = showRoomS3UploadService.getBoardList(pageable);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("galleryList", showRoomDtoList);

        return "admin-showroom";
    }

    @GetMapping("/admin/portfolio/save")
    public String showRoomSave(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "admin-showroom-save";
    }



}
