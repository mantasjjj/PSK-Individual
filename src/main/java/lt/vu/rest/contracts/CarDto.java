package lt.vu.rest.contracts;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Client;

@Getter
@Setter
public class CarDto {
    private String Make;

    private String Model;

    private String LicencePlate;

    private int Year;

    private Client Owner;
}
