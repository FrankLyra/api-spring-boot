package br.com.projetospring.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.projetospring.app.control.Mensagem;
import br.com.projetospring.app.model.Pessoa;
import br.com.projetospring.app.repository.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private Mensagem msg;
    @Autowired 
    private PessoaRepository repository;

    //metodo para cadastrar pessoas
    public ResponseEntity<?> cadastrarPessoa(Pessoa pessoa){
        if (pessoa.getNome().equals("")) {
            msg.setMensagem("O nome precisar ser preenchido");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }else if(pessoa.getIdade() < 0){
            msg.setMensagem("Informe uma idade válida");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repository.save(pessoa),HttpStatus.CREATED);
        }
    }

    //metodo para selecionar pessoas
    public ResponseEntity<?> selecionarPessoas(){
        return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
    }

    //metodo para buscar pessoa por id
    public ResponseEntity<?> selecionarByCodico(long id){
        if (repository.countById(id) == 0) {
            msg.setMensagem("Não foi encontrada nenhuma pessoa.");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
        }
    }

    //metodo para editar dados
    public ResponseEntity<?> editarPessoa(Pessoa pessoa){
        if(repository.countById(pessoa.getId()) == 0){
            msg.setMensagem("Usuário informado não existe.");
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }else if(pessoa.getNome().equals("")){
            msg.setMensagem("É necessário informar o nome.");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }else if(pessoa.getIdade() < 0){
            msg.setMensagem("É necessário informar a idade.");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(repository.save(pessoa), HttpStatus.OK);
        }
    }

    //remover pessoa
    @GetMapping("/app/{id}")
    public ResponseEntity<?> removerPessoa(long id){
        if (repository.countById(id)  == 0) {
            msg.setMensagem("O código informado não existe.");
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }else{
            Pessoa p = repository.findById(id);
            repository.delete(p);

            msg.setMensagem("Pessoa removida com sucesso.");
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }

    }

}
