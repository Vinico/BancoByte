package br.univates.banco.negocio;

public class ValorNegativoException extends Exception
{

    public ValorNegativoException()
    {
    }

    public ValorNegativoException(String message)
    {
        super(message);
    }
    
}
