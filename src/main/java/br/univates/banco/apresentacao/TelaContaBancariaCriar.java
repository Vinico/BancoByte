package br.univates.banco.apresentacao;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.Entrada;
import br.univates.alexandria.InvalidEntryException;
import br.univates.alexandria.persistence.DuplicatedKeyException;
import br.univates.alexandria.persistence.IDao;
import br.univates.alexandria.persistence.RecordNotFoundException;
import br.univates.alexandria.persistence.RecordNotReady;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.ContaBancariaEspecial;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.ContaBancariaDaoPostgres;
import br.univates.banco.persistencia.CorrentistaDaoPostgres;
import br.univates.banco.persistencia.DaoFactory;

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
            cpf = Entrada.leiaString("CPF Correntista: ",cpf);
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

                try {
                    daoConta.create(conta);
                } catch (DuplicatedKeyException e) {
                    throw new RuntimeException(e);
                } catch (RecordNotReady e) {
                    throw new RuntimeException(e);
                }

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
        
        
    }
}
