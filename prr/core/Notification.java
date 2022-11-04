package prr.core;

import java.io.Serializable;

public class Notification implements Serializable{
  private NotificationType _type;
  private Communication _com;

  public Notification(Communication com) {
    _com = com;
  }

  public void setType(TerminalMode current, TerminalMode changeTo){
    if(current == TerminalMode.OFF){
      if(changeTo == TerminalMode.SILENCE)
        _type = NotificationType.O2S;
      if(changeTo == TerminalMode.IDLE)
        _type = NotificationType.O2I;
    }
    if (current == TerminalMode.BUSY){
      if(changeTo == TerminalMode.SILENCE)
        _type = NotificationType.B2S;
      if(changeTo == TerminalMode.IDLE)
        _type = NotificationType.B2I;
    }
    if (current == TerminalMode.SILENCE)
      if (changeTo == TerminalMode.IDLE)
        _type = NotificationType.S2I;
  }

  public Communication com(){
    return _com;
  }

  public void send(){
    _com.getFrom().addNotification(this);
  }

  public String toString() {
  String res = "";
  if (_type == NotificationType.O2S)
    res = "O2S|";
  if (_type == NotificationType.O2I)
    res = "O2I|";
  if (_type == NotificationType.B2S)
    res = "B2S|";
  if (_type == NotificationType.B2I)
    res = "B2I|";
  if (_type == NotificationType.S2I)
    res = "S2I|";
  res += _com.getTo().getId();
  return res;
  }
  
}