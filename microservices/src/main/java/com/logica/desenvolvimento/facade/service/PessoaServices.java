package com.logica.desenvolvimento.facade.service;

import com.logica.desenvolvimento.facade.bean.PessoaBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PessoaServices {

    private static final Logger logger = Logger.getLogger(PessoaServices.class.getName());
    private final AtomicLong atomicLong = new AtomicLong();

    public List<PessoaBean> findAll() {
        logger.info("Finding all persons!");
        List<PessoaBean> pessoas = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            pessoas.add(mockPessoa());
        }
        return pessoas;
    }

    public PessoaBean findById(final String id) {
        logger.info("Finding one person!");
        return mockPessoa();
    }

    public PessoaBean create(final PessoaBean pessoaBean) {
        logger.info("Creating one person!");
        if (pessoaBean.getId() == null) {
            pessoaBean.setId(atomicLong.incrementAndGet());
        }
        return pessoaBean;
    }

    public PessoaBean update(final PessoaBean pessoaBean) {
        logger.info("Updating one person!");
        return pessoaBean;
    }

    public void delete(final String id) {
        logger.info("Deleting one person!");
    }

    private PessoaBean mockPessoa() {
        PessoaBean pessoaBean = new PessoaBean();
        pessoaBean.setId(atomicLong.incrementAndGet());
        pessoaBean.setNome("Rhuan");
        return pessoaBean;
    }
}
