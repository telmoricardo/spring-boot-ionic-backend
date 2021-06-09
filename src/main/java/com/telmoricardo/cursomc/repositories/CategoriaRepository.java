package com.telmoricardo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telmoricardo.cursomc.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
