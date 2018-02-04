/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminalserver;

/**
 *
 * @author Bruker
 */
public class SearchCommand implements ServerCommand
{

    @Override
    public String getName()
    {
        return "Search";
    }

    @Override
    public String process(String command, String[] arguments)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getShortDesc()
    {
        return "Hi";
    }

    @Override
    public String getLongDesc()
    {
        return "Hi";
    }
    
}
