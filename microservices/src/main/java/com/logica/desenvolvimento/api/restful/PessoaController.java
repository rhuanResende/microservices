package com.logica.desenvolvimento.api.restful;

import com.logica.desenvolvimento.entities.Pessoa;
import com.logica.desenvolvimento.facade.bean.PessoaBean;
import com.logica.desenvolvimento.facade.service.PessoaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaServices pessoaServices;

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa findById(@PathVariable(value = "id") final Long id) {
        return this.pessoaServices.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pessoa> findAllPerson() {
        return this.pessoaServices.findAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa create(@RequestBody PessoaBean pessoaBean) {
        return this.pessoaServices.create(pessoaBean);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Pessoa update(@RequestBody PessoaBean pessoaBean) {
        return this.pessoaServices.update(pessoaBean);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") final Long id) {
        this.pessoaServices.delete(id);
        return ResponseEntity.noContent().build();
    }
}
