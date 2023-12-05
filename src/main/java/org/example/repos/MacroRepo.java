package org.example.repos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.example.encryption.Encryption;
import org.example.models.Macro;
import javax.inject.Inject;
import java.util.List;

public class MacroRepo implements IMacroRepo {

    private Encryption encryption;
    private EntityManager entityManager;

    @Inject
    public MacroRepo(Encryption encryption){
        this.encryption = encryption;
        String persistenceUnitName = System.getProperty("env") != null ? "myPersistenceUnitTest" : "myPersistenceUnit";
        entityManager = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
    }

    @Override
    public void save(Macro macro) {
        entityManager.getTransaction().begin();
        encryptMacro(macro);
        entityManager.persist(macro);
        entityManager.getTransaction().commit();
    }

    @Override
    public Macro findById(Long id) {
        return decryptMacro(entityManager.find(Macro.class, id));
    }

    @Override
    public List<Macro> loadMacros() {
        TypedQuery<Macro> query = entityManager.createQuery("SELECT m FROM Macro m", Macro.class);
        List<Macro> macros = query.getResultList();
        decryptMacros(macros);
        return macros;
    }

    @Override
    public boolean existsByTrigger(String trigger) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(m) FROM Macro m WHERE m.trigger = :trigger", Long.class);
        query.setParameter("trigger", trigger);
        return query.getSingleResult() > 0;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Macro macro = entityManager.find(Macro.class, id);
        if (macro != null) {
            entityManager.remove(macro);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateMacro(Macro updatedMacro) {
        entityManager.getTransaction().begin();
        Macro macro = entityManager.find(Macro.class, updatedMacro.getId());
        if (macro != null) {
            encryptMacro(updatedMacro);
            macro.setTrigger(updatedMacro.getTrigger());
            macro.setTarget(updatedMacro.getTarget());
            entityManager.merge(macro);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void clearTable() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Macro").executeUpdate();
        entityManager.getTransaction().commit();
    }

    private void decryptMacros(List<Macro> macros) {
        for(Macro macro: macros){
            decryptMacro(macro);
            entityManager.detach(macro);
        }
    }

    private Macro decryptMacro(Macro macro){
        String target = macro.getTarget();
        String decryptedTarget = "";
        try {
            decryptedTarget = encryption.decrypt(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        macro.setTarget(decryptedTarget);
        return macro;
    }

    private void encryptMacro(Macro macro){
        String target = macro.getTarget();
        String encryptedTarget;
        try {
            encryptedTarget = encryption.encrypt(target);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        macro.setTarget(encryptedTarget);
    }
}
