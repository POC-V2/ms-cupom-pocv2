package com.pocms.cupons.repository;

import com.pocms.cupons.model.Cupom;
import org.springframework.data.repository.CrudRepository;

public interface CupomRepository extends CrudRepository<Cupom, String> {
}
