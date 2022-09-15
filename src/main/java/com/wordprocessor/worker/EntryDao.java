package com.wordprocessor.worker;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class EntryDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public EntryDTO saveEntry(EntryDTO entryDTO) {
        em.persist(entryDTO);
        return entryDTO;
    }

}
