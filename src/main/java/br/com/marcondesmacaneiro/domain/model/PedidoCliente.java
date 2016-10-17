package br.com.marcondesmacaneiro.domain.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 17/10/16.
 */
public class PedidoCliente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Cliente cliente;
    private Produto produto;
    private int status;
    private double quantidade;
    private double precoVenda;

    public PedidoCliente(Cliente cliente, Produto produto, int status, double quantidade, double precoVenda) {
        this.cliente = cliente;
        this.produto = produto;
        this.status = status;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }

    

}