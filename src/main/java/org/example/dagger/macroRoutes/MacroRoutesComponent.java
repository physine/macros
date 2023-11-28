package org.example.dagger.macroRoutes;

import dagger.Component;
import org.example.api.routes.MacroRoutes;
import org.example.dagger.macroService.MacroServiceModule;

@Component(modules = {MacroRoutesModule.class, MacroServiceModule.class})
public interface MacroRoutesComponent {
    MacroRoutes buildMacroRoutes();
}
