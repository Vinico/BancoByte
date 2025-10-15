package br.univates.banco.negocio;

import br.univates.banco.persistencia.ContaBancariaDao;
import br.univates.banco.persistencia.TransacaoDao;

import java.util.ArrayList;

public class ContaBancaria implements Comparable<ContaBancaria>
{
    private int numero;
    private Correntista correntista;
    protected double saldo;
    protected ArrayList<Transacao> transacoes = new ArrayList<Transacao>();
    TransacaoDao trasDao;

    
    private static int sequencial = 0;



    public ContaBancaria(Correntista correntista)
    {
        this.numero = ++sequencial;
        this.correntista = correntista;
        this.saldo = 0;
        trasDao = new TransacaoDao();

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
        trasDao = new TransacaoDao();
        transacoes = trasDao.getTransacoes(String.valueOf(this.numero));

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
        this.creditar("Depósito", valor);
        ContaBancariaDao contaDao = new ContaBancariaDao();
        contaDao.update(this);
    }
    
    protected void creditar(String descricao, double valor) throws ValorNegativoException
    {
        if (valor > 0)
        {
            this.saldo += valor;
            trasDao.create(new Transacao(descricao,valor,'C',this.saldo), String.valueOf(this.numero));
            ContaBancariaDao contaDao = new ContaBancariaDao();
            contaDao.update(this);
        }
        else
        {
            throw new ValorNegativoException();
        }
    }
    
    public void sacar(double valor) throws ValorNegativoException, SaldoInsuficienteException
    {
        this.debitar("Saque dinheio", valor);
        ContaBancariaDao contaDao = new ContaBancariaDao();
        contaDao.update(this);
    }
    
    public void fazerPix(double valor) throws ValorNegativoException, SaldoInsuficienteException
    {
        this.debitar("Pix", valor);
        ContaBancariaDao contaDao = new ContaBancariaDao();
        contaDao.update(this);
    }

    protected void debitar(String descricao, double valor) throws ValorNegativoException, SaldoInsuficienteException
    {
        if (valor <= 0)
        {
            throw new ValorNegativoException();
        }
        if (this.saldo - valor >=0)
        {
            this.saldo -= valor;
            trasDao.create(new Transacao(descricao,valor,'D',this.saldo), String.valueOf(this.numero));
            ContaBancariaDao contaDao = new ContaBancariaDao();
            contaDao.update(this);
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

    @Override
    public String toString() {
        return numero + ";" + correntista.getCpf().getNumeroCpf()+ ";" + saldo;
    }
}
