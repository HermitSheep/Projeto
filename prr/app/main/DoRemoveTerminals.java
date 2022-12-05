package prr.app.main;

import pt.*;
import java.util.*;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.TerminalNotFoundException;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.NetworkManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.forms.*;
import prr.app.exception.FileOpenFailedException;
import prr.core.exception.ImportFileException;
import prr.core.exception.MissingFileAssociationException;



public class DoRemoveTerminals extends Command<NetworkManager> {
    DoRemoveTerminals(NetworkManager receiver) {
        super(Label.REMOVE_TERMINAL, receiver);
        addIntegerField("coms", Message.terminalKey());
      }
      
      @Override
      protected final void execute(){
        Integer coms = integerField("coms");
    
        List<String> lines = _receiver.getNetwork().removeTerminals(coms);
        for (String line : lines)
            _display.addLine(line);
        _display.display();
      }
}
