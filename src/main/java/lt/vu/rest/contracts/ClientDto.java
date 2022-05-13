package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {

    private String Name;

    private lt.vu.entities.Car Car;

    private int mechanicId;

    private String carMake;

    private String licencePlate;
}
