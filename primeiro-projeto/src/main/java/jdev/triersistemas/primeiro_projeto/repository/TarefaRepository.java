package jdev.triersistemas.primeiro_projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jdev.triersistemas.primeiro_projeto.entity.CategoriaEntity;
import jdev.triersistemas.primeiro_projeto.entity.TarefaEntity;

@Repository
public interface TarefaRepository extends JpaRepository<TarefaEntity, Long> {

	@Query("SELECT t FROM TarefaEntity t WHERE t.completa = true")
	List<TarefaEntity> findCompleta();
	
	@Query("SELECT t FROM TarefaEntity t WHERE t.completa = false")
	List<TarefaEntity> findIncompleta();

	List<TarefaEntity> findAllByCategoriaOrderByIdAsc(CategoriaEntity categoria);
	
	List<TarefaEntity> findByCategoriaIdAndCompletaFalse(Long idCategoria);

	@Query("SELECT COUNT(t) FROM TarefaEntity t WHERE t.categoria.id =:idCategoria AND t.completa = :concluido")
	Long contarTarefasPorCategoriaEStatus(Long idCategoria, Boolean concluido);
	
	@Query("SELECT t FROM TarefaEntity t WHERE t.titulo LIKE %:titulo% ORDER BY t.id ASC")
	List<TarefaEntity> findAllByTituloOrderByIdAsc(@Param("titulo") String titulo);

}
