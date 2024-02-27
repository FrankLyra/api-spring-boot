package br.com.projetospring.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.projetospring.app.model.Cliente;


public interface ClienteRepository extends CrudRepository<Cliente, Integer>{
    Integer countById(int codigo);
}
