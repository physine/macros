package org.example.dagger.macroRepo;

import dagger.Module;
import dagger.Provides;
import org.example.encryption.Encryption;
import org.example.repos.MacroRepo;

@Module
public class MacroRepoModule {

    @Provides
    public MacroRepo provideMacroRepo(Encryption encryption) {
        return new MacroRepo(encryption);
    }
}
