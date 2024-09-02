package com.jovemdev.dados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jovemdev.dados.entity.JogadaEntity;

@Repository
public interface JogadaRepository extends JpaRepository<JogadaEntity, Long> {

	List<JogadaEntity> findAllJogadaByUsuarioId(Long id);
}
