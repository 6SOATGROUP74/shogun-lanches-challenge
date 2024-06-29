package com.example.demo.adapter.outbound.repository.mapper;

import com.example.demo.adapter.outbound.repository.entity.ProdutoEntity;
import com.example.demo.core.domain.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProdutoEntityMapper {

    ProdutoEntityMapper INSTANCE = Mappers.getMapper(ProdutoEntityMapper.class);

    ProdutoEntity mapFrom(Produto produto);
    Produto mapFrom(ProdutoEntity produto);
    List<Produto> mapFrom(List<ProdutoEntity> produto);


}
