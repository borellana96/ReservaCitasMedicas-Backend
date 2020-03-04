package com.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clinica.model.Paciente;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, String>{

}
