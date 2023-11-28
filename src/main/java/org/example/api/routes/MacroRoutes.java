package org.example.api.routes;

import com.google.gson.Gson;
import org.example.api.services.MacroService;
import org.example.models.Macro;
import org.example.models.ReqInfo;
import javax.inject.Inject;
import java.util.List;
import static spark.Spark.*;

public class MacroRoutes {

    private MacroService macroService;

    private Gson gson;

    @Inject
    public MacroRoutes(MacroService macroService){
        this.macroService = macroService;
        setupRoutes();
        this.gson = new Gson();
    }

    private void setupRoutes() {
        get("/api/v1/macros", (req, res) -> {
            System.out.println("Called the endpoint GET /api/v1/macros");
            List<Macro> macros = macroService.macros();
            String json = gson.toJson(macros);
            res.type("application/json");
            return json;
        });

        post("/api/v1/new-macro", (req, res) -> {
            Macro macro = gson.fromJson(req.body(), Macro.class);
            ReqInfo reqInfo = macroService.createMacro(macro);
            res.status(reqInfo.getStatus());
            return reqInfo.getMsg();
        });

        post("/api/v1/update-macro", (req, res) -> {
            Macro macro = gson.fromJson(req.body(), Macro.class);
            ReqInfo reqInfo = macroService.updateMacro(macro);
            res.status(reqInfo.getStatus());
            return reqInfo.getMsg();
        });
    }
}