package com.curso.api.gestaovendas.service;

import com.curso.api.gestaovendas.exception.entidadesEnum.EntidadesMsgException;
import com.curso.api.gestaovendas.responseDTO.CategoriaResponseDTO;
import com.curso.api.gestaovendas.exception.RegraNegocioException;
import com.curso.api.gestaovendas.model.Categoria;
import com.curso.api.gestaovendas.repository.CategoriaRepository;
import com.curso.api.gestaovendas.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvar(Categoria categoria){
        validarCategoriaDuplicada(categoria);
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> getAll(Pageable pageable){
        return categoriaRepository.findAll(pageable).getContent();
    }

    public Categoria getById(Long id){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if(categoriaOptional.isEmpty()){
            throw new EmptyResultDataAccessException(EntidadesMsgException.CATEGORIA.getEntidade() + " " + id, 1);
        }
        return categoriaOptional.get();
    }

    public List<Categoria> findByNome(String nome){
        Optional<List<Categoria>> optionalCategorias = categoriaRepository.getByNome(nome);
        if(optionalCategorias.isEmpty()){
            throw new EmptyResultDataAccessException(EntidadesMsgException.CATEGORIA.getEntidade() + " " + nome, 1);
        }else{
            return optionalCategorias.get();
        }
    }

    public Categoria atualizar(Categoria categoria){
        if(getById(categoria.getId())== null){
            throw new EmptyResultDataAccessException(EntidadesMsgException.CATEGORIA.getEntidade() + " " + categoria.getNome(),1);
        }
        validarCategoriaDuplicada(categoria);
        return categoriaRepository.save(categoria);
    }

    public void deletar(Long id){
        if(getById(id) == null){
            throw new EmptyResultDataAccessException(EntidadesMsgException.CATEGORIA.getEntidade()  + " " + id,1);
        }
        categoriaRepository.deleteById(getById(id).getId());
    }

    private void validarCategoriaDuplicada(Categoria categoria){
        Categoria categoriaIsPresent = categoriaRepository.findByNome(categoria.getNome());
        if(categoriaIsPresent != null && categoriaIsPresent.getId() != categoria.getId()){
            throw new RegraNegocioException(String.format("A categoria %s já esta cadastrada", categoria.getNome()));
        }
    }
}
