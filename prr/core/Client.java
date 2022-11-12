package prr.core;

import java.io.Serializable;
import java.util.*;

import javax.swing.TransferHandler;
import prr.core.exception.StateNotChangedException;


public class Client implements Serializable{
  private String _key;
  private String _name;
  private int _nif;
  private ClientLevel _level;
  private List<Terminal> _terminals;
  private long _payed;
  private long _debt;
  private boolean _notiSet;
  private TariffPlan _plan;
  protected List<Communication> _madeCommunications;
  protected List<Communication> _receivedCommunications;
  protected List<Notification> _notifications;

  public Client(String k, String n, int nif, TariffPlan plan){
      _name = n;
      _key = k;
      _nif = nif;
      _terminals = new ArrayList<>();
      _level = ClientLevel.NORMAL;
      _notiSet = true;
      _debt = 0;
      _payed = 0;
      _plan = plan;
      _madeCommunications = new ArrayList<>();
      _receivedCommunications = new ArrayList<>();
      _notifications = new ArrayList<>();
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

  public boolean getNotiSet() {
     return _notiSet;
  }

  public List<Terminal> getTerminals(){
      return _terminals;
  }

  public long getPayed(){
      return _payed;
  }

  public long getDebt(){
      return _debt;
  }

  public TariffPlan getPlan() {
    return _plan;
  }

  public void addDebt(double debt) {
    _debt += debt;
    checkUpgrade();
  }

  public void addPayed(double payed) {
    _payed += payed;
    checkUpgrade();
  }


  public void addMadeCom(Communication com) {
    _madeCommunications.add(com);
    checkUpgrade();
  }

  public void addReceivedCom(Communication com) {
    _receivedCommunications.add(com);
    checkUpgrade();
  }

  public void checkUpgrade(){
    if (_level == ClientLevel.NORMAL){
      if (_payed - _debt > 500)
        _level = ClientLevel.GOLD;
    }
    if (_level == ClientLevel.GOLD){
      if (_payed - _debt < 0)
        _level = ClientLevel.NORMAL;
      if(_madeCommunications.size() >= 5){
        for (int i = -1;i == -5; i--){
          if(!_madeCommunications.get(i).getType().equals("VIDEO"))
            return;
        }
      _level = ClientLevel.PLATINUM;
      }
    }
    if (_level == ClientLevel.PLATINUM){
      if (_payed - _debt < 0)
        _level = ClientLevel.NORMAL;
      if(_madeCommunications.size() >= 2){
        for (int i = -1;i == -2; i--){
          if(!_madeCommunications.get(i).getType().equals("TEXT"))
            return;
        }
      _level = ClientLevel.GOLD;
      }
    }
  }


  public void turnNotiOn() throws StateNotChangedException{
    if (_notiSet)
      throw new StateNotChangedException(_key, _notiSet);
    _notiSet = true;
  }

  public void turnNotiOff() throws StateNotChangedException{
    if (!_notiSet)
      throw new StateNotChangedException(_key, _notiSet);
    _notiSet = false;
  }

  public void addTerminal(Terminal term) {
    _terminals.add(term);
  }

  public void addNotification(Notification n1){
    for (Notification n: _notifications){
      if (n.com().getTo().getId() == n1.com().getTo().getId())
        return;
    }
    _notifications.add(n1);
  }

  public void deleteNotifications(){
    _notifications = new ArrayList<>();
  }

  public List<Notification> getNotifications() {
      return _notifications;
  }

  public boolean hasNotifications() {
      return !_notifications.isEmpty();
  }

  public int activeTerminals() {
      int res = 0;
      for (Terminal term : _terminals)
          if (term.isActive())
              res++;
      return res;
  }

  public String toString() {
    String client = ("CLIENT|" + _key + "|" + _name + "|" +                         //CLIENT|key|name
                    String.valueOf(_nif) + "|" + _level.name());                    //|Nif|level
    if (_notiSet)
        client += ("|YES");                                                         //|YES
    else
        client += ("|NO");                                                          //|NO
    client += ("|" + String.valueOf(_terminals.size()) + "|" + Math.round(_payed)   //|Active terminals|Payed
                + "|" + Math.round(_debt));                                   //|Debt
    return client;
  }

  public List<String> ComsMadeToString() {
    List<String> coms = new ArrayList<String>();
    for (Communication com : _madeCommunications) {
      coms.add(com.toString());
    }
    return coms;
  }

  public List<String> ComsReceivedToString() {
    List<String> coms = new ArrayList<String>();
    for (Communication com : _receivedCommunications) {
      coms.add(com.toString());
    }
    return coms;
  }
}
