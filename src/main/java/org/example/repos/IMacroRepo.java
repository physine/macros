package org.example.repos;

import org.example.models.Macro;

import java.util.List;

public interface IMacroRepo {
    boolean save(Macro macro);
    Macro findById(Long id);
    List<Macro> loadMacros();
    boolean existsByTarget(String target);
    boolean existsByTrigger(String trigger);
    boolean deleteById(Long id);
    boolean updateMacro(Macro macro);
    void clearTable();
}
