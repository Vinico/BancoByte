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

import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaCorrentistaEditar
{
    
    public void exibir()
    {
        String cpf = Entrada.leiaString("CPF: ");

        IDao<Correntista, String> daoCorre = DaoFactory.createCorrentistaDao();
        Correntista correntista = null;
        try {
            correntista = daoCorre.read(cpf);
        } catch (RecordNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (correntista != null)
        {
            String nome = correntista.getNome();
            String cidade = correntista.getMunicipio();

            nome = Entrada.leiaString("Nome: ",nome);
            cidade = Entrada.leiaString("Cidade: ",cidade);

            correntista.setNome(nome);
            correntista.setMunicipio(cidade);

            try {
                daoCorre.update(correntista);
            } catch (RecordNotFoundException e) {
                System.out.println("Conta não achada");
            }
        }
        else
        {
            System.out.println("Não localizamos o cadastro deste CPF");
        }
    }
}
