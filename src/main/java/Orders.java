import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Orders {
    private int id;
    private int id_Car;
    private int id_Client;
    private Date dateOfLoan;
    private Date dateOfReturn;

}
