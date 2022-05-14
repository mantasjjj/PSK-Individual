package lt.vu.alternatives;

import lt.vu.services.CarBrandGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;

@Alternative
@ApplicationScoped
public class DefaultCarBrandGenerator implements CarBrandGenerator, Serializable {

    public String generateCarBrand() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ignored) {
        }
        Integer randomInt = new Random().nextInt(4);

        return CarBrandGenerator.CarBrand.values()[randomInt].name();
    }
}
