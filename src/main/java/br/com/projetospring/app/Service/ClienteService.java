package br.com.projetospring.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projetospring.app.control.Mensagem;
import br.com.projetospring.app.model.Cliente;
import br.com.projetospring.app.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private Mensagem msg;

    public ResponseEntity<?> cadastrarCliente(Cliente cliente){
        if (repository.countById(cliente.getCodigo()) == 0) {
            msg.setMensagem("Cliente não encontrado.");    
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }else if (cliente.getEmail().equals("") || cliente.getNome().equals("")) {
            msg.setMensagem("Email e nome são obrigatórios");    
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }else{
            msg.setMensagem("Cliente Criado com sucesso.");
            return new ResponseEntity<>(repository.save(cliente), HttpStatus.OK);
        }
    }

}
