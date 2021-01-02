package travel.api.media.dto;

import lombok.Data;

/**
 * @Author: dd
 */
@Data
public class MediaRequestDTO {
    private Integer pageNo;
    private Integer pageSize;
    private String type;
}
