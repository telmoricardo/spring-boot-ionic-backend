package com.telmoricardo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telmoricardo.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
