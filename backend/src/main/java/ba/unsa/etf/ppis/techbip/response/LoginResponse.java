package ba.unsa.etf.ppis.techbip.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class LoginResponse {
    private final String tokenType = "Bearer ";
    private String token;
   /* private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String email;
    private String phoneNumber;
    private String username;
    private ArrayList<String> roles; */

}
