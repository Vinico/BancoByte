package br.univates.banco.apresentacao;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.Entrada;
import br.univates.alexandria.InvalidEntryException;
import br.univates.alexandria.persistence.IDao;
import br.univates.alexandria.persistence.RecordNotFoundException;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.CorrentistaDaoPostgres;
import br.univates.banco.persistencia.DaoFactory;

public class TelaCorrentistaDeletar
{
    
    public void exibir()
    {
        String cpf = Entrada.leiaString("CPF: ");

        IDao<Correntista, String> daoCorre = DaoFactory.createCorrentistaDao();
        IDao<ContaBancaria, Integer> daoConta = DaoFactory.createContaBancariaDao();
        Correntista correntista = null;
        try {
            correntista = daoCorre.read(cpf);
        } catch (RecordNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (correntista != null)
        {
            try {
                daoCorre.delete(correntista);
            } catch (RecordNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            System.out.println("Não localizamos o cadastro deste CPF");
        }
    }
}
