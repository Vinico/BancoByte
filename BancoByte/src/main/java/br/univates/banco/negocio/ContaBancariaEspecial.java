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

    public ContaBancariaEspecial(int numero, Correntista correntista, double saldo, double limite)
    {
        super(numero, correntista, saldo);
        this.limite = 1000;
        this.setLimite(limite);
    }

    public double getLimite()
    {
        return limite;
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
            trasDao.create(new Transacao(descricao,valor,'D',this.saldo), String.valueOf(super.getNumero()));
        }
        else
        {
            throw new SaldoInsuficienteException();
        }
    }
    
}
