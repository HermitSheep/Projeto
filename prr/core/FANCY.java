package prr.core;

import prr.core.exception.*;

public class FANCY extends Terminal {
  public FANCY(String id, Client client) {
    super(id, client);
  }

  public Communication makeVideoCall(Terminal to) throws InactiveTerminalException, UnsuportedAtOrigin, UnsuportedAtDestination, StateNotChangedException{
    _noComs = false;
    VIDEO vid = new VIDEO(this, to);
    set(TerminalMode.BUSY);
    if (!to.canStartCommunication())
      throw new InactiveTerminalException(to.getId(), to.getMode());
    _madeCommunications.add(vid);
    to.acceptVideoCall(this, vid);
    _ongoingCom = vid;
    _client.addMadeCom(vid);
    to.getClient().addReceivedCom(vid);
    return vid;
  }

  protected void acceptVideoCall(Terminal to, VIDEO vid) {
    _noComs = false;
    _receivedCommunications.add(vid);
    _ongoingCom = vid;
  }
}
