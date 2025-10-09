package br.univates.alexandria.menu1;

public abstract class MenuModel // Business Layer
{   
    private String titulo;
    private char[] atalhos;
    private String[] opcoes;

    public MenuModel(int quantOpcoes)
    {
        this.titulo = "*** M E N U ***";
        this.atalhos = new char[quantOpcoes];
        this.opcoes = new String[quantOpcoes];
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

    public void addOpcao( char atalho, String opcao )
    {
        for (int i = 0; i < opcoes.length; i++)
        {
            String op = opcoes[i];
            if (op == null)
            {
                this.atalhos[i] = Character.toLowerCase(atalho);
                this.opcoes[i] = opcao.toUpperCase().charAt(0)+opcao.toLowerCase().substring(1);
                i = opcoes.length;
            }
        }
    }
    
    public String getMenuString()
    {
        String menu = titulo+"\n\n";
        for (int i = 0; i < opcoes.length; i++)
        {
            String op = opcoes[i];
            if (op != null)
            {
                menu += "["+atalhos[i]+"] "+op+"\n";
            }
        }
        menu += "\nOpção:\n\n";
        return menu;
    }
    
    
    public abstract void executarOpcao( char op );
   
    
}
