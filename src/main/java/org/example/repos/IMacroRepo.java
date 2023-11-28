package org.example.repos;

import jakarta.transaction.Transactional;
import org.example.models.Macro;

import java.util.List;

public interface IMacroRepo {
    void save(Macro macro);
    Macro findById(Long id);
    List<Macro> loadMacros();
    boolean existsByTrigger(String trigger);
    void deleteById(Long id);
    void updateMacro(Macro macro);
    void clearTable();
}
