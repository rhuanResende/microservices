package com.logica.desenvolvimento.facade.service;

import com.logica.desenvolvimento.core.exception.ResourceNotFoundException;
import com.logica.desenvolvimento.core.exception.ValidationException;
import com.logica.desenvolvimento.entities.Pessoa;
import com.logica.desenvolvimento.facade.bean.PessoaBean;
import com.logica.desenvolvimento.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class PessoaServices {

    @Autowired
    private PessoaRepository pessoaRepository;

    private static final Logger logger = Logger.getLogger(PessoaServices.class.getName());
    private final AtomicLong atomicLong = new AtomicLong();

    public List<Pessoa> findAll() {
        logger.info("Finding all persons!");
        return this.pessoaRepository.findAll()
                .stream()
                .sorted((p1, p2) -> p1.getId().compareTo(p2.getId()))
                .collect(Collectors.toList());
    }

    public Pessoa findById(final Long id) {
        logger.info("Finding one person!");
        return this.pessoaRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para o ID informado."));
    }

    public Pessoa create(final PessoaBean pessoaBean) {
        logger.info("Creating one person!");
        return this.pessoaRepository.saveAndFlush(beanToEntity(pessoaBean));
    }

    public Pessoa update(final PessoaBean pessoaBean) {
        logger.info("Updating one person!");
        Pessoa pessoa = this.findById(pessoaBean.getId());
        pessoa.setNome(pessoaBean.getNome());
        return this.pessoaRepository.saveAndFlush(pessoa);
    }

    public void delete(final Long id) {
        logger.info("Deleting one person!");
        this.pessoaRepository.delete(this.findById(id));
    }

    private PessoaBean mockPessoa() {
        PessoaBean pessoaBean = new PessoaBean();
        pessoaBean.setId(atomicLong.incrementAndGet());
        pessoaBean.setNome("Rhuan");
        return pessoaBean;
    }

    private Pessoa beanToEntity(PessoaBean pessoaBean) {
        Pessoa pessoa = new Pessoa();
        if (pessoaBean.getNome() == null) {
            throw new ValidationException("Nome deve ser informado.");
        }

        if (pessoaBean.getId() != null) {
            pessoa.setId(pessoa.getId());
        }
        pessoa.setNome(pessoaBean.getNome().trim().toUpperCase());;
        return pessoa;
    }
}
