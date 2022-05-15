package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.CarColorGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateCarColor implements Serializable {
    @Inject
    CarColorGenerator carColorGenerator;

    private CompletableFuture<String> carColorGeneratorTask = null;

    @LoggedInvocation
    public String generateNewCarColor() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        carColorGeneratorTask = CompletableFuture.supplyAsync(() -> carColorGenerator.generateColor());

        return "/clientDetails.xhtml?faces-redirect=true&clientId=" + requestParameters.get("clientId");
    }

    public boolean isCarColorGeneratorRunning() {
        return carColorGeneratorTask != null && !carColorGeneratorTask.isDone();
    }

    public String getCarColorGeneratorStatus() throws ExecutionException, InterruptedException {
        if (carColorGeneratorTask == null) {
            return null;
        } else if (isCarColorGeneratorRunning()) {
            return "Car color generation in progress";
        }
        return "Suggested car color: " + carColorGeneratorTask.get();
    }
}
