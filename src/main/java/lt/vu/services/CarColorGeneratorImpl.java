package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class CarColorGeneratorImpl implements CarColorGenerator, Serializable {

    @Override
    public String generateColor() {
        Integer randomInt = new Random().nextInt(3);

        return CarColorGenerator.CarColor.values()[randomInt].name();
    }
}
