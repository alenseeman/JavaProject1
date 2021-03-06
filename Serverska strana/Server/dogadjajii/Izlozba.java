package dogadjajii;
import osobe.*;
import java.util.*;
import java.io.*;

public class Izlozba extends Dogadjaj
{
  protected String tema;
  protected ArrayList<String> autori;
  
  public Izlozba()
  {
    super();
  }
  
  public Izlozba(String n,String dp,String dk,String vp,String vk,String o,Organizator org,ArrayList<Ucesnik>uc,ArrayList<File> f,String te,ArrayList<String> aut)
  {
    super(n,dp,dk,vp,vk,o,org,uc,f);
    tema=te;
    autori=aut;
  }
  
  public String getTema()
  {
    return tema;
  }
  public ArrayList<String> getAutori()
  {
    return autori;
  }
  
  public String csvToString()
  {
    return super.csvToString()+","+tema;
  }
}