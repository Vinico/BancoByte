package br.univates.banco.apresentacao;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.Entrada;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.CorrentistaDaoPostgres;

public class TelaCorrentistaDeletar
{
    
    public void exibir()
    {
        String cpf = Entrada.leiaString("CPF: ");

        CorrentistaDaoPostgres dao = new CorrentistaDaoPostgres();
        Correntista correntista = dao.read(cpf);

        if (correntista != null)
        {
            dao.delete(correntista);
        }
        else
        {
            System.out.println("Não localizamos o cadastro deste CPF");
        }
    }
}
