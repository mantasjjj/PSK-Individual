package lt.vu.services;

import lombok.Getter;
import lombok.Setter;
import lt.vu.exceptions.InvalidLicencePlateFormatException;
import lt.vu.exceptions.LicencePlateAlreadyExistsException;
import lt.vu.exceptions.LicencePlateException;
import lt.vu.persistence.CarDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

@ApplicationScoped
public class LicencePlateChecker implements Serializable {

    @Inject
    @Setter
    @Getter
    private CarDAO carDAO;

    public void checkLicencePlate(String licencePlate) throws LicencePlateException {
        String regex = "[A-Z]{3}[0-9]{3}";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(licencePlate);

        if (matcher.matches()) {
            if (carDAO.findByLicencePlate(licencePlate) != null) {
                throw new LicencePlateAlreadyExistsException(format("Licence plate %s already exists.", licencePlate));
            }
        } else {
            throw new InvalidLicencePlateFormatException("Licence plate is in invalid format.");
        }

    }
}
