package br.univates.banco.apresentacao;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.Entrada;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.ContaBancariaEspecial;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.ContaBancariaDaoPostgres;
import br.univates.banco.persistencia.CorrentistaDaoPostgres;

public class TelaContaBancariaCriar
{
    
    public void exibir()
    {
        String cpf = "";
        char tipo = ContaBancaria.TIPO_SIMPLES;
        double limite = 1000;
        ContaBancaria conta;
        
        boolean repetir = true;
        while (repetir)
        {
            try
            {
                cpf = Entrada.leiaString("CPF Correntista: ",cpf);
                
                CorrentistaDaoPostgres dao = new CorrentistaDaoPostgres();
                Correntista correntista = dao.read( new Cpf(cpf) );
                
                if (correntista != null)
                {
                    tipo = Entrada.leiaChar("Tipo de conta [S]imples ou [E]special: ",tipo);
                    if (tipo == 'E')
                    {
                        limite = Entrada.leiaDouble("Limite da conta: ",limite);
                        conta = new ContaBancariaEspecial(correntista,limite);
                    }
                    else
                    {
                        conta = new ContaBancaria(correntista);
                    }
                    ContaBancariaDaoPostgres daoConta = new ContaBancariaDaoPostgres();
                    daoConta.create(conta);
                
                    repetir = false;
                }
                else
                {
                     char sn = Entrada.leiaChar("Correntista não existe.\nQuer tentar novamente (S/N)?",'S');
                     if (Character.toUpperCase(sn) == 'N')
                     {
                         repetir = false;
                     }
                }
            } 
            catch (InvalidEntryException ex)
            {
                System.out.println("CPF inválido");
            }
        }
        
        
    }
}
