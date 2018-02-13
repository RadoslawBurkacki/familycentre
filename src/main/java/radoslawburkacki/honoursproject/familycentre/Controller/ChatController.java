package radoslawburkacki.honoursproject.familycentre.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import radoslawburkacki.honoursproject.familycentre.Service.ChatService;
import radoslawburkacki.honoursproject.familycentre.Model.Message;

@RestController
public class ChatController {

    @Autowired
    ChatService chatService;

    @RequestMapping(method = RequestMethod.POST, value = "/chat/")
    public ResponseEntity CheckIfUserIsFamilyMemberById(@RequestBody Message m) {


        return chatService.addMessage(m);


    }
}
