package lt.vu.services;

public interface CarColorGenerator {
    enum CarColor {
        RED, BLUE, WHITE, BLACK
    }

    String generateColor();
}
