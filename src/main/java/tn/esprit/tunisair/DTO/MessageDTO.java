package tn.esprit.tunisair.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.tunisair.Service.UserServiceimpl;
import tn.esprit.tunisair.entity.Message;
import tn.esprit.tunisair.entity.User;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;
    private String content;
    private UserDTO sender;
    private UserDTO receiver;

    // Constructeurs, getters, setters

        // ... autres propriétés ...

        public Message toEntity(UserServiceimpl userService) {
            Message message = new Message();
            message.setId(id);


            User senderUser = userService.getUserById(sender.getId());
            User receiverUser = userService.getUserById(receiver.getId());



            return message;
        }

        // ... autres méthodes ...
    }


    // Autres méthodes et propriétés si nécessaires

