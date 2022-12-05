package prr.app.main;

import prr.core.NetworkManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.forms.*;

import java.io.IOException;
import java.util.*;
import prr.app.exception.FileOpenFailedException;
import prr.core.exception.ImportFileException;
import prr.core.exception.MissingFileAssociationException;


public class DoShowBestClientBasic extends Command<NetworkManager> {
    DoShowBestClientBasic(NetworkManager receiver) {
        super(Label.REMOVE_TERMINAL, receiver);
        addIntegerField("coms", Message.coms());
      }
      
      @Override
      protected final void execute(){    
        int coms = integerField("coms");
        try {List<String> lines = _receiver.getNetwork().ClientBestBasic();
        for (String line : lines)
            _display.addLine(line);
        _display.display();
      }
      catch (IOException a) {
        _display.add("não há clientes com terminais basic");
        _display.display();
    }
    }

}
