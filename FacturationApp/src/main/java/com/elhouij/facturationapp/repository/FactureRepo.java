package com.elhouij.facturationapp.repository;

import com.elhouij.facturationapp.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepo extends JpaRepository<Facture, Integer> {
}
