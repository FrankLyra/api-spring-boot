package br.com.projetospring.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projetospring.app.model.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa,Long>{
   
    List<Pessoa> findAll();
    Pessoa findById(long id);   
    List<Pessoa> findByOrderByNome();
    List<Pessoa> findByNomeOrderByIdadeDesc(String nome); 
    List<Pessoa> findByNomeContaining(String termo);
    List<Pessoa> findByNomeStartsWith(String termo);
    List<Pessoa> findByNomeEndsWith(String termo);

    @Query(value = "SELECT SUM(idade) FROM pessoas", nativeQuery = true)
    int somaIdades();

    @Query(value = "SLECT * FROM pessoas WHERE idade >= :idade", nativeQuery = true)
    List<Pessoa> idadeMaioIgual(int idade);

    Integer countById(long id);
}
