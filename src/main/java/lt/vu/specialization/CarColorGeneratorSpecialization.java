package lt.vu.specialization;

import lt.vu.services.CarColorGeneratorImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;

import static lt.vu.services.CarColorGenerator.CarColor.RED;

@ApplicationScoped
@Specializes
public class CarColorGeneratorSpecialization extends CarColorGeneratorImpl {
    @Override
    public String generateColor() {
        return RED.name();
    }
}
