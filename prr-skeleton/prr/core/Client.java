package prr.core;

import java.util.*;

import static prr.core.ClientLevel.*;

public class Client {
  private String _key;         //FIXME tem de ser uma String, o stor disse
  private String _name;
  private int _nif;
  private ClientLevel _level;
  private List<Terminal> _terminals;
  private int _payed;
  private int _debt;

  public Client(String k, String n, int nif){
      _name = n;
      _key = k;
      _nif = nif;
      _terminals = new ArrayList<>();
      _level = ClientLevel.NORMAL;
  }

  public String getKey(){
    return _key;
}

    public String getName(){
        return _name;
    }

    public int getNif() {
        return _nif;
    }

    public ClientLevel getLevel(){
        return _level;
    }

    public List<Terminal> getTerminals(){
        return _terminals;
    }

    public int getPayed(){
        return _payed;
    }

    public int getDebt(){
        return _debt;
    }

    public List<Notification> getNotifications() {
        List<Notification> noti = new ArrayList<Notification>();
        for (Terminal term : _terminals) {
            if (!term.getNotifications().isEmpty())
            noti.addAll(term.getNotifications());
        }
        return noti;
    }

    public boolean hasNotifications() {
        for (Terminal term : _terminals) {
            if (!term.getNotifications().isEmpty())
            return true;
        }
        return false;
    }

    public int activeTerminals() {
        int res = 0;
        for (Terminal term : _terminals)
            if (term.isActive())
                res++;
        return res;
    }

    public String clientToString() {
        String client = ("CLIENT" + String.valueOf(_key) + "|" + _name + "|" +      //CLIENT|key|name|
                        String.valueOf(_nif) + "|" + _level.name());                //nif|level
        if (hasNotifications())
          client += ("|YES");                                                       //|YES
        else
          client += ("|NO");                                                        //|NO
        client += ("|" + String.valueOf(activeTerminals()) + "|" + String.valueOf(_payed)   //|
                    + "|" + String.valueOf(_debt));
        return client;
    }
}
