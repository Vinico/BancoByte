package br.univates.banco.apresentacao;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.Entrada;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.CorrentistaDaoPostgres;

public class TelaCorrentistaCadastrar
{
    
    public void exibir()
    {
        String cpf = "";
        String nome = "";
        String cidade = "";

        boolean repetir = true;
        while (repetir)
        {
            try
            {
                cpf = Entrada.leiaString("CPF: ",cpf);
                nome = Entrada.leiaString("Nome: ",nome);
                cidade = Entrada.leiaString("Cidade: ",cidade);

                Correntista correntista = new Correntista( new Cpf(cpf), nome, cidade );
                CorrentistaDaoPostgres dao = new CorrentistaDaoPostgres();
                dao.create(correntista);
                
                repetir = false;
            } 
            catch (InvalidEntryException ex)
            {
                System.out.println("CPF inválido");
            }
        }
        
        
    }
}
