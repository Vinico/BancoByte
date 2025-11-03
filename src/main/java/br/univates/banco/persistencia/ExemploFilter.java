package br.univates.banco.persistencia;

import br.univates.alexandria.persistence.IDao;
import br.univates.banco.negocio.Correntista;
import java.util.ArrayList;

public class ExemploFilter
{
    public static void main(String[] args)
    {
        IDao<Correntista,String> dao = DaoFactory.createCorrentistaDao();
        
        ArrayList<Correntista> lista = dao.readAll();
        
        for (Correntista c: lista)
        {
            Filtro f = new Filtro();
            if (f.isAccept(c))
            {
                System.out.println( c.getNome() );
            }
        }
    }
    
    
    
}
