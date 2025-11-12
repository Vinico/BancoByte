package br.univates.banco.negocio;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transacao
{
    private LocalDate data;
    private String descricao;
    private double valor;
    private char tipoOperacao; // D ou C
    private double saldo;
    
    public Transacao(String descricao, double valor, char tipoOperacao, double saldo)
    {
        /*
        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int mes = Calendar.getInstance().get(Calendar.MONTH )+1;
        int ano = Calendar.getInstance().get(Calendar.YEAR );
        this.data = LocalDate.of(ano, mes, dia);
        */
        this.data = LocalDate.now();
        this.descricao = descricao;
        this.valor = valor;
        this.tipoOperacao = tipoOperacao;
        this.saldo = saldo;
    }
    
    public Transacao(LocalDate dt, String descricao, double valor, char tipoOperacao, double saldo)
    {
        this.data = dt;
        this.descricao = descricao;
        this.valor = valor;
        this.tipoOperacao = tipoOperacao;
        this.saldo = saldo;
    }
    
    public String getTransacaoFormatada()
    {
        DecimalFormat doubleFormatador = new DecimalFormat("###,##0.00");
        DateTimeFormatter dateFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return this.data.format(dateFormatador)+ " " +String.format("%-5s",this.descricao)+"\t"+
               String.format("%10s","R$"+doubleFormatador.format(this.valor)+" "+this.tipoOperacao)+"\t"+
               String.format("%10s","R$"+doubleFormatador.format(this.saldo));
        
        
    }

    public String toString()
    {
        DateTimeFormatter dateFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.data.format(dateFormatador)+";"+this.descricao+";"+this.valor+";"+this.tipoOperacao+";"+this.saldo;
    }

    public LocalDate getData()
    {
        return data;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public double getValor()
    {
        return valor;
    }

    public char getTipoOperacao()
    {
        return tipoOperacao;
    }

    public double getSaldo()
    {
        return saldo;
    }
    
    
    
}
