package br.univates.banco.negocio;

import java.util.ArrayList;

public class ContaBancariaEspecial extends ContaBancaria
{
    private double limite;

    public ContaBancariaEspecial(Correntista correntista)
    {
        super(correntista);
        this.limite = 1000;
    }
    
     public ContaBancariaEspecial(Correntista correntista,double limite)
    {
        super(correntista);
        this.limite = 1000;
        this.setLimite(limite);
    }

    public ContaBancariaEspecial(int numero, Correntista correntista, double saldo, ArrayList<Transacao> transacoes, double limite)
    {
        super(numero, correntista, saldo, transacoes);
        this.limite = 1000;
        this.setLimite(limite);
    }

    public double getLimite()
    {
        return limite;
    }
    
    public char getTipo()
    {
        return ContaBancaria.TIPO_ESPECIAL;
    }

    public void setLimite(double limite)
    {
        if (limite > 0)
        {
            this.limite = limite;
        }
    }

    @Override
    protected void debitar(String descricao, double valor) throws ValorNegativoException, SaldoInsuficienteException
    {
        if (valor < 0)
        {
            throw new ValorNegativoException();
        }
        if (this.saldo + this.limite - valor >=0)
        {
            this.saldo -= valor;
            this.listaTransacoes.add( new Transacao(descricao,valor,'D',this.saldo) );
        }
        else
        {
            throw new SaldoInsuficienteException();
        }
    }
    
    public String toString()
    {
        return super.toString()+";"+this.limite;
    }
    
    
}
