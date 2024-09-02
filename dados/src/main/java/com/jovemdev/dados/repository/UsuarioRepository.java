package com.jovemdev.dados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jovemdev.dados.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	
	@Query("SELECT u FROM UsuarioEntity u ORDER BY u.percentualAcerto LIMIT 5")
	List<UsuarioEntity> findBests();
}
