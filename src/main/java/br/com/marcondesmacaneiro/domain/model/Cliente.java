package br.com.marcondesmacaneiro.domain.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 17/10/16.
 */
public class Cliente implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String bairro;
    private String rua;
    private String numero;
    private String telefone;
    private Municipio municipio;
    private String dataNascimento;
    
    public Cliente(int id, String nome, String cpf, String email, String bairro, String rua, String numero, String telefone, Municipio municipio, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.telefone = telefone;
        this.municipio = municipio;
        this.dataNascimento = dataNascimento;
    }

     
}
