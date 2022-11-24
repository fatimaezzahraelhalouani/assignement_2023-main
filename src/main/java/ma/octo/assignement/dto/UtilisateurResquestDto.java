package ma.octo.assignement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurResquestDto implements Serializable {
    //information that i will send about user
    private String username;
    private String lastname;
    private String firstname;
    private String gender;
    private String password;
    private Date birthdate;

}
