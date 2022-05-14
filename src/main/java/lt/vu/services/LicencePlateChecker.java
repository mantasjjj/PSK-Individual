package lt.vu.services;

import lt.vu.exceptions.LicencePlateException;

public interface LicencePlateChecker {

    void checkLicencePlate(String licencePlate) throws LicencePlateException;
}
