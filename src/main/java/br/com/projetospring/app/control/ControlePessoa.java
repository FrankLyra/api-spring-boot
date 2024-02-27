package br.com.projetospring.app.control;

import org.springframework.web.bind.annotation.RestController;

import br.com.projetospring.app.Service.PessoaService;
import br.com.projetospring.app.model.Pessoa;
import br.com.projetospring.app.repository.PessoaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ControlePessoa {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/app")
    public ResponseEntity<?> findAll() {
        return pessoaService.selecionarPessoas();
    }

    @GetMapping("/app/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return pessoaService.selecionarByCodico(id);
    }

    @PostMapping("/createPessoa")
    public ResponseEntity<?> create(@RequestBody Pessoa p) {
        return pessoaService.cadastrarPessoa(p);
    }

    @PutMapping("/app/update")
    public Pessoa update(@RequestBody Pessoa p) {
        return pessoaRepository.save(p);
    }

    @DeleteMapping("/app/{id}")
    public ResponseEntity<?> remove(@PathVariable long id) {
        return pessoaService.removerPessoa(id);
    }

    @GetMapping("/app/contador")
    public long count(){
        return pessoaRepository.count();
    }

    @GetMapping("/app/buscarOrdenadoPorNome")
    public List<Pessoa> buscarOrdenadoPorNome(){
        return pessoaRepository.findByOrderByNome();
    }

    @GetMapping("/app/buscarOrdenadoPorNomePorIdade")
    public List<Pessoa> buscarOrdenadoPorNomePorIdade(String nome){
        return pessoaRepository.findByNomeOrderByIdadeDesc(nome);
    }

    @GetMapping("/app/{termo}")
    public List<Pessoa> nomeContem(String termo){
        return pessoaRepository.findByNomeContaining(termo);
    }

    @GetMapping("/app/buscarNomesIniciamCom/{termo}")
    public List<Pessoa> buscarNomesIniciamCom(String termo){
        return pessoaRepository.findByNomeStartsWith(termo);
    }

    @GetMapping("/app/buscarNomesFinalizamCom/{termo}")
    public List<Pessoa> buscarNomesFinalizamCom(String termo){
        return pessoaRepository.findByNomeEndsWith(termo);
    }

    @GetMapping("/app/somaIdades")
    public Integer somaIdades(){
        return pessoaRepository.somaIdades();
    }

    @GetMapping("/app/idadeMaiorIgual/{idade}")
    public List<Pessoa> idadeMaiorIgual(int idade){
        return pessoaRepository.idadeMaioIgual(idade);
    }

    @GetMapping("/status")
    public ResponseEntity<?> Status() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
