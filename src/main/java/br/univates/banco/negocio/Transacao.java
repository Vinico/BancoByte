package br.univates.banco.negocio;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transacao implements Comparable<Transacao>{

    private LocalDateTime dataTransacao;
    private String descricao;

    private double valor;
    private int tipo; // 0 pra credito,  1 pra débito

    private String destinatario;

    public Transacao(String descricao, double valor, int tipo, String destinatario) {
        this.dataTransacao = LocalDateTime.now();
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.destinatario = destinatario;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public int getTipo() {
        return tipo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getTransacao(){
        String retorno = "";
        if(tipo == 1){
            retorno = String.format(dataTransacao + " | Destinatario: %s  | Débito | R$%.2f | | Descrição: %s",destinatario,  valor, descricao );

        }
        if(tipo == 0){
            retorno = String.format(dataTransacao + " | De: %s  | Crédito | R$%.2f | | Descrição: %s",destinatario,  valor, descricao );

        }

        return retorno;
    }


    @Override
    public int compareTo(Transacao o) {
        return dataTransacao.compareTo(o.dataTransacao);
    }
}
