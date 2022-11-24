package ma.octo.assignement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurResponseDto {
    //information that i will receive about the user
    private String username;
    private String lastname;
    private String firstname;
    private String gender;
    private Date birthdate;
}
