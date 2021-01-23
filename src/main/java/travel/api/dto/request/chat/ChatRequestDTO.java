package travel.api.dto.request.chat;

import lombok.Data;
import travel.api.util.page.PageRequest;

/**
 * @Author: dd
 */
@Data
public class ChatRequestDTO extends PageRequest {
    private Long fromUser;
    private Long toUser;

}
