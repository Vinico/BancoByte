package br.univates.banco.negocio;

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
    public void sacar(double valor) throws ValorNegativoException, SaldoInsuficienteException
    {
        if (valor < 0)
        {
            throw new ValorNegativoException();
        }
        if (this.saldo + this.limite - valor >=0)
        {
            this.saldo -= valor;
        }
        else
        {
            throw new SaldoInsuficienteException();
        }
    }
    
}
