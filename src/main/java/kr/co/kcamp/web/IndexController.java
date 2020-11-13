package kr.co.kcamp.web;

import kr.co.kcamp.config.auth.LoginUser;
import kr.co.kcamp.config.auth.dto.SessionUser;
import kr.co.kcamp.domain.DealerUser;
import kr.co.kcamp.service.cars.*;
import kr.co.kcamp.service.popup.PopUpS3UploadService;
import kr.co.kcamp.service.posts.GuestBookService;
import kr.co.kcamp.service.posts.NoticeService;
import kr.co.kcamp.service.posts.PostsService;
import kr.co.kcamp.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final NoticeService noticeService;
    private final BookCarService bookCarService;
    private final GuestBookService guestBookService;
    private final ShowRoomS3UploadService showRoomS3UploadService;
    private final HttpSession httpSession;
    private final DirectCarsS3UploadService directCarsS3UploadService;



    @GetMapping("/")
    public String indexx(Model model, @LoginUser SessionUser user) {
        model.addAttribute("notice", noticeService.findTop3Desc());
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        return "indextest";
    }

    @GetMapping("/about")
    public String about(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "about";
    }

    @GetMapping("/aboutcar")
    public String aboutcar(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "aboutcar";
    }

    @GetMapping("/snslogin")
    public String snslogin(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "snslogin";
    }

    @GetMapping("/personlogin")
    public String personlogin(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "regilogin";
    }

    @GetMapping("/carprocess")
    public String carprocess(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "carprocess";
    }

    @GetMapping("/cometous")
    public String cometous(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "cometous";
    }

    @GetMapping("/financial")
    public String financial(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "financial";
    }

    @GetMapping("/location")
    public String location(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        return "contactus";
    }

    @GetMapping("/schedule")
    public String scheduletest(Model model, @LoginUser SessionUser user, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }
        model.addAttribute("schedulelist", bookCarService.findAllDesc());
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("posts", postsService.getBoardList(pageable));

        return "scheduletest";
    }

    @GetMapping("/schedulePopup")
    public String schedulePopup(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        return "schedulePopup";
    }

    @GetMapping("/contactus")
    public String contactus(Model model, @LoginUser SessionUser user, @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("guestbook", guestBookService.findAllDesc());

        return "qaboard";
    }

    @GetMapping("/password/{id}")
    public String pasw(Model model, @LoginUser SessionUser user, @PathVariable Long id) {
        if(user != null) {
            model.addAttribute("uName", user.getName());
        }
        GuestBookResponseDto dto = guestBookService.findById(id);
        model.addAttribute("guestbook",dto);

        return "password";
    }

    @GetMapping("/contactus/detail/{id}")
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

        return "contactus-detail";
    }



    @GetMapping("/reservation")
    public String reservation(Model model, @LoginUser SessionUser user,  @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {  //model은 서버템플릿엔진에서 사용할 수 있는 객체 저장 여기서는 결과는 posts로 index.mustache에 전달

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("posts", postsService.getBoardList(pageable));

        return "reservation";
    }

    @GetMapping("/gallery")
    public String gallerymain(Model model, @LoginUser SessionUser user,  @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {  //model은 서버템플릿엔진에서 사용할 수 있는 객체 저장 여기서는 결과는 posts로 index.mustache에 전달

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        List<DirectCarsDto> directCarsDtoList = directCarsS3UploadService.getBoardList(pageable);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("galleryList", directCarsDtoList);

        return "gallerymain";
    }

    @GetMapping("/gallery/detail/{id}")
    public String directCarsDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        DirectCarsDto dto = directCarsS3UploadService.findById(id);
        model.addAttribute("gallerycar",dto);

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        return "gallery-detail";
    }

    @GetMapping("/portfolio")
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

        return "portfolio";
    }

    @GetMapping("/portfolio/detail/{id}")
    public String showRoomDetail(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        ShowRoomDto dto = showRoomS3UploadService.findById(id);
        model.addAttribute("showroom",dto);

        if(user != null) {
            model.addAttribute("uName", user.getName());
        }

        DealerUser dealerUser = (DealerUser)httpSession.getAttribute("user1");
        if(dealerUser != null) {
            model.addAttribute("dName", dealerUser.getName());
        }

        return "portfolio-detail";
    }




}
