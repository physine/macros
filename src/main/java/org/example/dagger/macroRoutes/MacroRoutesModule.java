package org.example.dagger.macroRoutes;

import dagger.Module;
import dagger.Provides;
import org.example.api.routes.MacroRoutes;
import org.example.api.services.MacroService;

@Module
public class MacroRoutesModule {

    @Provides
    public MacroRoutes providesMacroRoutes(MacroService macroService){
        return new MacroRoutes(macroService);
    }
}
