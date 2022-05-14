package lt.vu.services;

public interface CarBrandGenerator {
    enum CarBrand {
        Audi,
        BMW,
        Mercedes,
        VW,
        Toyota
    }
    String generateCarBrand();
}
