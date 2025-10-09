package br.univates.alexandria;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CpfTeste
{
    public static void main(String[] args)
    {
        try
        {
            Cpf cpf = new Cpf("58759565003",true);
            System.out.println("Aceitou CPF inválido");
            if (!cpf.isValido())
            {
                System.out.println("E é mesmo inválido");
            }
        } 
        catch (InvalidEntryException ex)
        {
            System.out.println("CPF inválido");
        }
    }
}
