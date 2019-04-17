package cars.resource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.annotations.*;

@SwaggerDefinition(info = @Info(description = "API documentation", version = "V0.0.1", title = "CARS API"))
@ApplicationPath("/api/v1")
public class RestApplication extends Application {

}
