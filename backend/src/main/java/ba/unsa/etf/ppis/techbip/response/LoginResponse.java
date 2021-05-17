package ba.unsa.etf.ppis.techbip.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private final String tokenType = "Bearer ";
    private String token;
}
