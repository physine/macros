package org.example.dagger.macroRepo;

import dagger.Component;
import org.example.repos.MacroRepo;

@Component(modules = MacroRepoModule.class)
public interface MacroRepoComponent {
    MacroRepo buildMacroRepo();
}
