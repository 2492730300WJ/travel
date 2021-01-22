package travel.api.ws.dto;

import lombok.Data;

/**
 * @Author: dd
 */
@Data
public class CommonMsgRequestDTO {
    private Long from;

    private Long to;

    private String message;

    private Integer type;

    private Integer msgType;

    private String isMy;
}
