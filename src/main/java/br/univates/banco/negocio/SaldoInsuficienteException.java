package br.univates.banco.negocio;

public class SaldoInsuficienteException extends Exception
{

    public SaldoInsuficienteException()
    {
    }

    public SaldoInsuficienteException(String message)
    {
        super(message);
    }
    
}
