package tn.esprit.tunisair.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tunisair.Repository.MessageRepository;
import tn.esprit.tunisair.Service.MessageService;
import tn.esprit.tunisair.Service.UserServiceimpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("send")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserServiceimpl userService;


    @GetMapping("/ask")
    public String askQuestion(@RequestParam("question") String question) {
        return messageService.getResponse(question);
    }


}































