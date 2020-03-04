package com.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clinica.model.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario,String>{

}
