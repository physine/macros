package org.example.api.services;

import org.example.keyProcessing.macroMatcher.MacroMatcher;
import org.example.models.Macro;
import org.example.models.ReqInfo;
import org.example.repos.MacroRepo;

import javax.inject.Inject;
import java.util.List;

public class MacroService {

    private MacroRepo macroRepo;
    private MacroMatcher macroMatcher;

    @Inject
    public MacroService(MacroRepo macroRepo, MacroMatcher macroMatcher){
        this.macroRepo = macroRepo;
        this.macroMatcher = macroMatcher;
    }

    public ReqInfo macros(){
        List<Macro> macros = macroRepo.loadMacros();
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setStatus(201);
        reqInfo.setData(macros);
        reqInfo.setMsg("macro created");
        return reqInfo;
    }

    public ReqInfo createMacro(Macro macro){
        macroRepo.save(macro);
        updateMacroMatcherState();
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setStatus(201);
        reqInfo.setMsg("macro created");
        return reqInfo;
    }

    public ReqInfo updateMacro(Macro macro){
        macroRepo.updateMacro(macro);
        updateMacroMatcherState();
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setStatus(200);
        reqInfo.setMsg("macro updated");
        return reqInfo;
    }

    public ReqInfo deleteMacro(Long id){
        macroRepo.deleteById(id);
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setStatus(200);
        reqInfo.setMsg("macro deleted");
        return reqInfo;
    }

    private void updateMacroMatcherState(){
        macroMatcher.setMacros(macroRepo.loadMacros());
    }
}
