package br.com.marcondesmacaneiro.domain.model;

import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 17/10/16.
 */
public class Produto {
    private static final long serialVersionUID = 1L;
       
    private int id;
    private String descricao;
    private double valorCusto;
    private int estoque;
    private Categoria categoria;

    public Produto(int id, String descricao, double valorCusto, int estoque, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valorCusto = valorCusto;
        this.estoque = estoque;
        this.categoria = categoria;
    }

    
    
    
}
