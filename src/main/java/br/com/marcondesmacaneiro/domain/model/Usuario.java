package br.com.marcondesmacaneiro.domain.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by marcondesmacaneiro on 17/10/16.
 */
public class Usuario implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nome;
    private String login;
    private String senha;

    public Usuario(int id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

   
    
    
}
