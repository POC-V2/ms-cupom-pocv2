package com.pocms.cupons.api.impl;

import com.pocms.cupons.api.CupomApi;
import com.pocms.cupons.exception.CupomNotFoundException;
import com.pocms.cupons.model.Cupom;
import com.pocms.cupons.repository.CupomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping
public class CupomApiImpl implements CupomApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(CupomApiImpl.class);

    private final CupomRepository repository;

    public CupomApiImpl(CupomRepository repository) {
        this.repository = repository;
    }

    @Override
    @GetMapping("{id}")
    public BigDecimal one(@NonNull @PathVariable("id") String id) {
        LOGGER.info("Buscando o cupom: id = {}", id);

        var cupom = repository.findById(id)
                .orElseThrow(() -> new CupomNotFoundException(id));

        return cupom.getDesconto();
    }

    @Override
    @PostMapping
    public Cupom newCupom(@NonNull @RequestParam("desconto") BigDecimal desconto) {
        LOGGER.info("Inserindo um novo cupom: desconto = {}", desconto);

        var cupom = new Cupom(desconto);
        return repository.save(cupom);
    }

}
