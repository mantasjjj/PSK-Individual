package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class CarBrandGenerator implements Serializable {

    public enum CarBrand {
        Audi,
        BMW,
        Mercedes,
        VW,
        Toyota
    }

    public String generateCarBrand() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        Integer randomInt = new Random().nextInt(4);

        return CarBrand.values()[randomInt].name();
    }
}
