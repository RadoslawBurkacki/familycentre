package radoslawburkacki.honoursproject.familycentre.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import radoslawburkacki.honoursproject.familycentre.CrudRepo.MessageRepository;
import radoslawburkacki.honoursproject.familycentre.Model.Message;

@Service
public class ChatService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    FCMService fcmTokenService;


    public ResponseEntity addMessage(Message m){


        messageRepository.save(m);

        fcmTokenService.sendPrivateChatMessage(m);



        return new ResponseEntity<>( HttpStatus.CREATED);
    }

}
