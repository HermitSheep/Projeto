package prr.core;

import prr.core.exception.*;

public class FANCY extends Terminal {
  public FANCY(String id, Client client) {
    super(id, client);
    _type = "FANCY";
  }

  public Communication makeVideoCall(Terminal to) throws UnavailableTerminalException, UnsuportedAtOrigin, UnsuportedAtDestination, StateNotChangedException{
    _noComs = false;
    VIDEO vid = new VIDEO(this, to);
    if (!to.canStartCommunication() || to.getMode() == TerminalMode.SILENCE){
      vid.getTo().failedCom(vid);
      throw new UnavailableTerminalException(to.getId(), to.getMode());
    }
    if (to.getType().equals("BASIC"))
      throw new UnsuportedAtDestination(to.getId());
    set(TerminalMode.BUSY);
    vid.setOngoing(true);
    to.acceptVideoCall(this, vid);
    _madeCommunications.add(vid);
    _ongoingCom = vid;
    _client.addMadeCom(vid);
    to.getClient().addReceivedCom(vid);
    return vid;
  }

  protected void acceptVideoCall(Terminal to, VIDEO vid) throws StateNotChangedException {
    _noComs = false;
    set(TerminalMode.BUSY);
    _receivedCommunications.add(vid);
    _ongoingCom = vid;
  }
}
