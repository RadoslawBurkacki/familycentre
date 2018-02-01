package radoslawburkacki.honoursproject.familycentre.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import radoslawburkacki.honoursproject.familycentre.Model.Message;

@Service
public class ChatService {

    @Autowired MessageRepository messageRepository;


    public ResponseEntity addMessage(Message m){


        messageRepository.save(m);

        return new ResponseEntity<>( HttpStatus.CREATED);
    }

}
