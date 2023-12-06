package org.example.api.services;

import org.example.api.requestInfoFactory.RequestInfoFactory;
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

    public ReqInfo createMacro(Macro macro){
        boolean operationResult = macroRepo.save(macro);
        ReqInfo reqInfo = RequestInfoFactory.createReq(operationResult);
        if (operationResult)
            updateMacroMatcherState();
        return reqInfo;
    }

    public ReqInfo retrieveMacros(){
        List<Macro> operationResult = macroRepo.loadMacros();
        ReqInfo reqInfo = RequestInfoFactory.retrieveReq(operationResult);
        return reqInfo;
    }

    public ReqInfo updateMacro(Macro macro){
        boolean operationResult = macroRepo.updateMacro(macro);
        ReqInfo reqInfo = RequestInfoFactory.updateReq(operationResult);
        if (operationResult)
            updateMacroMatcherState();
        return reqInfo;
    }

    public ReqInfo deleteMacro(Long id){
        boolean operationResult = macroRepo.deleteById(id);
        ReqInfo reqInfo = RequestInfoFactory.deleteReq(operationResult);
        if (operationResult)
            updateMacroMatcherState();
        return reqInfo;
    }

    private void updateMacroMatcherState(){
        macroMatcher.setMacros(macroRepo.loadMacros());
    }
}
