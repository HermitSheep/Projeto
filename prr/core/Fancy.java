package prr.core;


public class Fancy extends Terminal {
  public Fancy(String id, Client client) {
    super(id, client);
  }

  public void makeVideoCall(Terminal to) {
    _noComs = false;
    //FIXME implement method
  }

  protected void acceptVideoCall(Terminal to) {
    _noComs = false;
    //FIXME implement method
  }
}
