package com.logica.desenvolvimento.api.restful;

import com.logica.desenvolvimento.facade.bean.PessoaBean;
import com.logica.desenvolvimento.facade.service.PessoaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaServices pessoaServices;

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PessoaBean findById(@PathVariable(value = "id") final String id) {
        return this.pessoaServices.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PessoaBean> findAllPerson() {
        return this.pessoaServices.findAll();
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PessoaBean create(@RequestBody PessoaBean pessoaBean) {
        return this.pessoaServices.create(pessoaBean);
    }

    @RequestMapping(method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PessoaBean update(@RequestBody PessoaBean pessoaBean) {
        return this.pessoaServices.update(pessoaBean);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") final String id) {
        this.pessoaServices.delete(id);
    }
}
