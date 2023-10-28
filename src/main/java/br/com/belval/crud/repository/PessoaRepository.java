package br.com.belval.crud.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.belval.crud.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {
	  Pessoa findById(int id);
	  
	  List<Pessoa> findByAtivo(boolean ativo);

}
