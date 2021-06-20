package com.telmoricardo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.telmoricardo.cursomc.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
