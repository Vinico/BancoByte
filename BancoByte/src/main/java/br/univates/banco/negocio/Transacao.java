package br.univates.banco.negocio;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Transacao
{
    private LocalDate data;
    private String descricao;
    private double valor;
    private char tipoOperacao; // D ou C
    private double saldo;

    public Transacao(String descricao, double valor, char tipoOperacao, double saldo)
    {
        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int mes = Calendar.getInstance().get(Calendar.MONTH )+1;
        int ano = Calendar.getInstance().get(Calendar.YEAR );
        this.data = LocalDate.of(ano, mes, dia);
        this.descricao = descricao;
        this.valor = valor;
        this.tipoOperacao = tipoOperacao;
        this.saldo = saldo;
    }

    public Transacao(LocalDate data, String descricao, double valor, char tipoOperacao, double saldo) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.tipoOperacao = tipoOperacao;
        this.saldo = saldo;
    }

    public String getTransacaoFormatada()
    {

        
        DateTimeFormatter dateFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String space = "                 ";
        String aux = this.descricao+space.substring(this.descricao.length());

        return this.data.format(dateFormatador)+ " " +aux+"\t"+
               String.format("%10.2f",this.valor)+"\t"+String.format("%10.2f",this.saldo); 
        
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String[] bagulhos;
        bagulhos = new String[]{data.format(dateFormatador), descricao, Double.toString(valor), Character.toString(tipoOperacao),  Double.toString(saldo)};
        return String.join(";", bagulhos);
    }
}
