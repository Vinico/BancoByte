package br.univates.alexandria.menu1;

import br.univates.alexandria.Entrada;

public final class Menu // View
{
    private MenuModel model;

    public Menu(int quantOpcoes)
    {
        this.model = new DefaultMenuModel(quantOpcoes);
    }
    
    public Menu(MenuModel model)
    {
        this.model = model;
    }
    
    public void setTitulo(String titulo)
    {
        this.model.setTitulo(titulo);
    }
    
    public char gerarMenu()
    {
        char op = ' ';
        while (op != 'x')
        {
            op = Entrada.leiaChar( model.getMenuString() );
            op = Character.toLowerCase(op);
            model.executarOpcao(op);
        }
        return op;
    }
    
    public void addOpcao(char atalho, String opcao)
    {
        model.addOpcao(atalho, opcao);
    }

    public MenuModel getModel()
    {
        return model;
    }

    public void setModel(MenuModel model)
    {
        this.model = model;
    }
    
    private class DefaultMenuModel extends MenuModel
    {

        public DefaultMenuModel(int quantOpcoes)
        {
            super(quantOpcoes);
        }

        @Override
        public void executarOpcao(char op)
        {
            System.out.println("Implemente um menu model herdando de MenuModel");
        }
    }
    
}
