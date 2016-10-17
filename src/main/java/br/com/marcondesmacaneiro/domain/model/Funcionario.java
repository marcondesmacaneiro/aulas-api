package br.com.marcondesmacaneiro.domain.model;

import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 17/10/16.
 */
public class Funcionario {
    
    private int funcionarioId;
    private String cpf;
    private String nome;
    private String endereco;
    private String email;
    private boolean usuario;
    private String login;
    private String senha;
    private String confirmaSenha;
    
    public Funcionario(int funcionarioId, String cpf, String nome, String endereco, String email, boolean usuario) {
        this.funcionarioId = funcionarioId;
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.usuario = usuario;
    }

    
   
}
