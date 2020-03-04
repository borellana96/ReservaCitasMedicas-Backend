package com.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clinica.model.Horario;

@Repository
public interface IHorarioRepository extends JpaRepository<Horario,Integer>{

}
