package org.example.keyProcessing.macroMatcher;

import org.example.models.Macro;

import java.util.List;

public class MacroMatcher implements IMacroMatcher {

    private List<Macro> macros;

    private static MacroMatcher instance = null;

    private MacroMatcher(){
    }

    public static MacroMatcher getInstance(){
        if (instance == null){
            instance = new MacroMatcher();
        }
        return instance;
    }

    public void setMacros(List<Macro> macros) {
        this.macros = macros;
    }

    public Macro matchMacroTrigger(String keyHistory) {
        for(Macro macro: macros){
            String trigger = macro.getTrigger();
            if (keyHistory.contains(trigger))
                return macro;
        }
        return null;
    }
}
