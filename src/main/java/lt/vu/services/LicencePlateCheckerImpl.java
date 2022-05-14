package lt.vu.services;

import lombok.Getter;
import lombok.Setter;
import lt.vu.exceptions.InvalidLicencePlateFormatException;
import lt.vu.exceptions.LicencePlateAlreadyExistsException;
import lt.vu.exceptions.LicencePlateException;
import lt.vu.persistence.ClientDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

@ApplicationScoped
public class LicencePlateCheckerImpl implements LicencePlateChecker, Serializable {

    @Inject
    @Setter
    @Getter
    private ClientDAO clientDAO;

    public void checkLicencePlate(String licencePlate) throws LicencePlateException {
        String regex = "[A-Z]{3}\\d{3}";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(licencePlate);

        if (matcher.matches()) {
            if (clientDAO.loadAll() != null && clientDAO.loadAll().stream().anyMatch(c -> Objects.equals(c.getCarLicencePlate(), licencePlate))) {
                throw new LicencePlateAlreadyExistsException(format("Licence plate %s already exists.", licencePlate));
            }
        } else {
            throw new InvalidLicencePlateFormatException("Licence plate is in invalid format.");
        }

    }
}
