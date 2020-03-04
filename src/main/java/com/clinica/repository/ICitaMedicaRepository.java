package com.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clinica.model.CitaMedica;

@Repository
public interface ICitaMedicaRepository extends JpaRepository<CitaMedica,Integer>{

}
