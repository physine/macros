package org.example.dagger.macroService;

import dagger.Component;
import org.example.api.services.MacroService;

@Component(modules = MacroServiceModule.class)
public interface MacroServiceComponent {
    MacroService buildMacroService();
}
