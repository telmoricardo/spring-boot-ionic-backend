package com.telmoricardo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telmoricardo.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
