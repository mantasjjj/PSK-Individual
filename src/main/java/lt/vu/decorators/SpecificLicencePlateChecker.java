package lt.vu.decorators;

import lt.vu.exceptions.LicencePlateException;
import lt.vu.services.LicencePlateChecker;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.Objects;

@Decorator
public abstract class SpecificLicencePlateChecker implements LicencePlateChecker {

    @Inject
    @Delegate
    @Any
    LicencePlateChecker licencePlateChecker;

    @Override
    public void checkLicencePlate(String licencePlate) throws LicencePlateException {
        licencePlateChecker.checkLicencePlate(licencePlate);
        if (Objects.equals(licencePlate, "MAN123")) {
            throw new LicencePlateException("Licence plate is reserved");
        }
    }
}
