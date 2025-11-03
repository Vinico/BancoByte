package br.univates.banco.apresentacao;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.Entrada;
import br.univates.alexandria.InvalidEntryException;
import br.univates.alexandria.persistence.DuplicatedKeyException;
import br.univates.alexandria.persistence.IDao;
import br.univates.alexandria.persistence.RecordNotReady;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.CorrentistaDaoPostgres;
import br.univates.banco.persistencia.DaoFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                IDao<Correntista, String> daoCorre = DaoFactory.createCorrentistaDao();
                daoCorre.create(correntista);
                
                repetir = false;
            } 
            catch (InvalidEntryException ex)
            {
                System.out.println("CPF inválido");
            } 
            catch (DuplicatedKeyException ex)
            {
                System.out.println("Este correntista já existe, o CPF já está usado");
            } 
            catch (RecordNotReady ex)
            {
                System.out.println("Falta algum dos campos obrigatórios");
            }
        }
        
        
    }
}
