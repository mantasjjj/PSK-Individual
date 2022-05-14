package lt.vu.specialization;

import lt.vu.services.CarColorGeneratorImpl;

import javax.enterprise.inject.Specializes;

import static lt.vu.services.CarColorGenerator.CarColor.RED;

@Specializes
public class CarColorGeneratorSpecialization extends CarColorGeneratorImpl {
    @Override
    public String generateColor() {
        return RED.name();
    }
}
