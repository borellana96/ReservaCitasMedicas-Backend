package com.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clinica.model.Medico;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico,Integer> {

}
