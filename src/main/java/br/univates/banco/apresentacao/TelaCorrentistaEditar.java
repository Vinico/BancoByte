package br.univates.banco.apresentacao;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.Entrada;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.CorrentistaDao;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaCorrentistaEditar
{
    
    public void exibir()
    {
        String cpf = Entrada.leiaString("CPF: ");

        CorrentistaDao dao = new CorrentistaDao();
        Correntista correntista = dao.read(cpf);

        if (correntista != null)
        {
            String nome = correntista.getNome();
            String cidade = correntista.getMunicipio();

            nome = Entrada.leiaString("Nome: ",nome);
            cidade = Entrada.leiaString("Cidade: ",cidade);

            correntista.setNome(nome);
            correntista.setMunicipio(cidade);
            
            dao.update(correntista);
        }
        else
        {
            System.out.println("Não localizamos o cadastro deste CPF");
        }
    }
}
