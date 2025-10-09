package br.univates.alexandria.menu3;

import br.univates.alexandria.Entrada;
import java.util.ArrayList;

public final class Menu // View
{
    private String titulo;
    private ArrayList<MenuItem> itens;

    public Menu()
    {
        this.titulo = "*** M E N U ***";
        this.itens = new ArrayList<MenuItem>();
    }
    
    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        if (!titulo.isBlank())
        {
            this.titulo = titulo;
        }
    }
    
    private String getMenuString()
    {
        String menu = titulo+"\n\n";
        
        for (MenuItem item: itens)
        {
            menu += item.getItemFormatado()+"\n";
        }
        menu += "[x] Sair\n";
        menu += "\nOpção:\n\n";
        return menu;
    }
    
    
    public char gerarMenu()
    {
        char op = ' ';
        while (op != 'x')
        {
            op = Entrada.leiaChar( this.getMenuString() );
            op = Character.toLowerCase(op);
            
            for (MenuItem item: itens)
            {
                if (item.getAtalho() == op)
                {
                    item.executar();
                }
            }
        }
        return op;
    }
    
    public void addItem( MenuItem item )
    {
        itens.add(item);
    }

}
