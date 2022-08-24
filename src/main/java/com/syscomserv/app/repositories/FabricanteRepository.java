package com.syscomserv.app.repositories;

import com.syscomserv.app.models.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FabricanteRepository extends JpaRepository<Fabricante, Integer> {
}
