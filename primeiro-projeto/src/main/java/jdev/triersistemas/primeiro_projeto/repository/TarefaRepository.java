package jdev.triersistemas.primeiro_projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jdev.triersistemas.primeiro_projeto.entity.TarefaEntity;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {

	@Query("SELECT t FROM TarefaEntity t WHERE t.completa = true")
	List<TarefaEntity> findCompleta();
	
	@Query("SELECT t FROM TarefaEntity t WHERE t.completa = false")
	List<TarefaEntity> findIncompleta();

}
