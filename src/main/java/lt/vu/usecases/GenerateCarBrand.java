package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.CarBrandGenerator;
import lt.vu.services.JerseyNumberGenerator;

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
public class GenerateCarBrand implements Serializable {
    @Inject
    CarBrandGenerator carBrandGenerator;

    private CompletableFuture<String> carBrandGeneratorTask = null;

    @LoggedInvocation
    public String generateNewCarBrand() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        carBrandGeneratorTask = CompletableFuture.supplyAsync(() -> carBrandGenerator.generateCarBrand());

        return  "/clientDetails.xhtml?faces-redirect=true&clientId=" + requestParameters.get("clientId");
    }

    public boolean isCarBrandGeneratorRunning() {
        return carBrandGeneratorTask != null && !carBrandGeneratorTask.isDone();
    }

    public String getCarBrandGeneratorStatus() throws ExecutionException, InterruptedException {
        if (carBrandGeneratorTask == null) {
            return null;
        } else if (isCarBrandGeneratorRunning()) {
            return "Car Brand generation in progress";
        }
        return "Suggested car brand: " + carBrandGeneratorTask.get();
    }
}
