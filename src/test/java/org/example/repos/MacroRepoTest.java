package org.example.repos;

import org.example.encryption.Encryption;
import org.example.models.Macro;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MacroRepoTest {

    private static MacroRepo macroRepo;

    @BeforeClass
    public static void beforeClass(){
        System.setProperty("env", "test");
        macroRepo = new MacroRepo(new Encryption());
        macroRepo.clearTable();
    }

    @AfterClass
    public static void afterClass(){
        System.clearProperty("env");
    }

    @After
    public void after(){
        macroRepo.clearTable();
    }

    @Test
    public void saveTest(){
        Macro macro = new Macro("test1", "test2");
        macroRepo.save(macro);
        List<Macro> macros = macroRepo.loadMacros();
        assertTrue(macros.contains(macro));
    }

    @Test
    public void loadMacrosTest() {
        Macro macro1 = new Macro("trigger1", "target1");
        Macro macro2 = new Macro("trigger2", "target2");
        macroRepo.save(macro1);
        macroRepo.save(macro2);
        List<Macro> macros = macroRepo.loadMacros();
        assertEquals(2, macros.size());
        assertTrue(macros.contains(macro1));
        assertTrue(macros.contains(macro2));
    }

    @Test
    public void clearTableTest() {
        assertEquals(0, macroRepo.loadMacros().size());
        macroRepo.save(new Macro("trigger1", "target1"));
        macroRepo.save(new Macro("trigger2", "target2"));
        assertEquals(2, macroRepo.loadMacros().size());
        macroRepo.clearTable();
        assertEquals(0, macroRepo.loadMacros().size());
    }

    @Test
    public void updateMacroTest() {
        assertEquals(0, macroRepo.loadMacros().size());
        Macro macro = new Macro("trigger", "target");
        macroRepo.save(macro);
        macro.setTarget("target_upgraded");
        macroRepo.updateMacro(macro);
        List<Macro> macros = macroRepo.loadMacros();
        assertEquals(1, macros.size());
        assertEquals("target_upgraded", macros.get(0).getTarget());
    }
}
