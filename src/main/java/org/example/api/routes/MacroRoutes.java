package org.example.api.routes;

import com.google.gson.Gson;
import org.example.api.services.MacroService;
import org.example.models.Macro;
import org.example.models.ReqInfo;
import javax.inject.Inject;

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

        // create
        post("/api/v1/new-macro", (req, res) -> {
            Macro macro = gson.fromJson(req.body(), Macro.class);
            ReqInfo reqInfo = macroService.createMacro(macro);
            res.type("application/json");
            res.status(reqInfo.getStatus());
            return reqInfo.getResponse();
        });

        // retrieve
        get("/api/v1/macros", (req, res) -> {
            ReqInfo reqInfo = macroService.retrieveMacros();
            res.type("application/json");
            res.status(reqInfo.getStatus());
            return reqInfo.getResponse();
        });


        // update
        post("/api/v1/update-macro", (req, res) -> {
            Macro macro = gson.fromJson(req.body(), Macro.class);
            ReqInfo reqInfo = macroService.updateMacro(macro);
            res.status(reqInfo.getStatus());
            return reqInfo.getResponse();
        });

        // delete
        delete("/api/v1/delete-macro", (req, res) -> {
            Long id = gson.fromJson(req.body(), Long.class);
            ReqInfo reqInfo = macroService.deleteMacro(id);
            res.status(reqInfo.getStatus());
            return reqInfo.getResponse();
        });
    }
}
