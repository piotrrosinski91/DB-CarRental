import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Cars {
    private int id;
    private String mark;
    private String model;
    private int yearOfProduction;
    private int capacity;
    private String fuelType;
    private int doors;
    private String regNumber;
    private BigDecimal price;
}
