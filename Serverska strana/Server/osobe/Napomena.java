package osobe;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class Napomena implements Serializable
{
  protected String datumKreiranja;
  protected String tekstNapomene;
  
  public Napomena()
  {
    super();
  }
  public Napomena(String tn)
  {
    super();
    tekstNapomene=tn;
    SimpleDateFormat sdf=new SimpleDateFormat("dd.mm.yyyy.");
    datumKreiranja=sdf.format(new Date());
    
  }
  public Napomena(String dk,String tn)
  {
    super();
    datumKreiranja=dk;
    tekstNapomene=tn;
  }
  
  public String getDatumKreiranja()
  {
    return datumKreiranja;
  }
  public String getTekstNapomene()
  {
    return tekstNapomene;
  }
  public void setTekstNapomene(String sa)
  {
    tekstNapomene=sa;
  }
  public void setDatumKreiranja(String dk)
  {
    datumKreiranja=dk;
  }
}

