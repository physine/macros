package org.example.dagger.macroService;

import dagger.Module;
import dagger.Provides;
import org.example.api.services.MacroService;
import org.example.keyProcessing.macroMatcher.MacroMatcher;
import org.example.repos.MacroRepo;

@Module
public class MacroServiceModule {
    @Provides
    public MacroService providesMacroService(MacroRepo macroRepo, MacroMatcher macroMatcher) {
        return new MacroService(macroRepo, macroMatcher);
    }

    @Provides
    public MacroMatcher providesMacroMatcher(){
        return MacroMatcher.getInstance();
    }
}
