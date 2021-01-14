package travel.api.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String userId;
    String userCard;
    String password;
    String token;
    String refreshToken;
    Long phone;
}