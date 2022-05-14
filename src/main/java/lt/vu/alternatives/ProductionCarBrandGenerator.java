package lt.vu.alternatives;

import lt.vu.services.CarBrandGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.util.Random;

@Alternative
@ApplicationScoped
public class ProductionCarBrandGenerator implements CarBrandGenerator {

    public String generateCarBrand() {
        Integer randomInt = new Random().nextInt(4);

        return CarBrandGenerator.CarBrand.values()[randomInt].name();
    }
}
