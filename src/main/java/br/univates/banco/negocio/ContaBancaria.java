package br.univates.banco.negocio;

public class ContaBancaria implements Comparable<ContaBancaria>
{
    private int numero;
    private Correntista correntista;
    protected double saldo;
    
    private static int sequencial = 0;

    public ContaBancaria(Correntista correntista)
    {
        this.numero = ++sequencial;
        this.correntista = correntista;
        this.saldo = 0;
    }

    public static void setSequencial(int sequencial)
    {
        ContaBancaria.sequencial = sequencial;
    }
    
    
    
    
    public ContaBancaria(int numero, Correntista correntista, double saldo)
    {
        this.numero = numero;
        this.correntista = correntista;
        this.saldo = saldo;
    }

    public int getNumero()
    {
        return numero;
    }
    
    public char getTipo()
    {
        return 'S'; // Simples
    }

    public Correntista getCorrentista()
    {
        return correntista;
    }

    public double getSaldo()
    {
        return saldo;
    }

    public void depositar(double valor) throws ValorNegativoException
    {
        if (valor > 0)
        {
            this.saldo += valor;
        }
        else
        {
            throw new ValorNegativoException();
        }
    }

    public void sacar(double valor) throws ValorNegativoException, SaldoInsuficienteException
    {
        if (valor <= 0)
        {
            throw new ValorNegativoException();
        }
        if (this.saldo - valor >=0)
        {
            this.saldo -= valor;
        }
        else
        {
            throw new SaldoInsuficienteException();
        }
    }
    
    @Override
    public boolean equals(Object conta)
    {
        boolean ok = false;
        if (conta != null && conta instanceof ContaBancaria)
        {
            ContaBancaria c = (ContaBancaria)conta;
            ok = (this.numero == c.numero);
        }
        return ok;
    }

    @Override
    public int compareTo(ContaBancaria outraConta)
    {
        return (this.numero - outraConta.numero);
    }
    
}
