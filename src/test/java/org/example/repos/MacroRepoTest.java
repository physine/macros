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
    public void inputLengthMustBeMultipleOf16() {
        Macro macro1 = new Macro("!one", "1111111111");
        macroRepo.save(macro1);
        List<Macro> macros1 = macroRepo.loadMacros();
        assertEquals(1, macros1.size());
        assertTrue(macros1.contains(macro1));

        Macro macro2 = new Macro("!two", "222222222");
        macroRepo.save(macro2);
        List<Macro> macros2 = macroRepo.loadMacros();
        assertEquals(2, macros2.size());
        assertTrue(macros2.contains(macro2));

        Macro macro3 = new Macro("!three", "3333333333");
        macroRepo.save(macro3);
        List<Macro> macros3 = macroRepo.loadMacros();
        assertEquals(3, macros3.size());
        assertTrue(macros3.contains(macro3));
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
