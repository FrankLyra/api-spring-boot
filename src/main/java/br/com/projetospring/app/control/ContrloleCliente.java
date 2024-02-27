package br.com.projetospring.app.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.projetospring.app.Service.ClienteService;
import br.com.projetospring.app.model.Cliente;
import jakarta.validation.Valid;

@Controller
public class ContrloleCliente {
    
    @Autowired
    private ClienteService service;

    @PostMapping("/cadastraCliente")
    public void cadastraCliente(@Valid @RequestBody Cliente cliente){
        service.cadastrarCliente(cliente);
    }

}
