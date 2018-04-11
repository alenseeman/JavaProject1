package osobe;
import java.util.*;
import java.io.*;

public class Ucesnik extends Osoba
{
  protected String nazivOrganizacije;
  
  public Ucesnik()
  {
    super();
  }
  
  public Ucesnik(String i,String p,String no)
  {
    super(i,p);
    nazivOrganizacije=no;
  }
  
  public void setNazivOrganizacije(String naz)
  {
    nazivOrganizacije=naz; 
  }
  public String getNazivOrganizacije()
  {
    return nazivOrganizacije;
  }
  
  public String toString()
  {
    return ime+" "+prezime+" "+nazivOrganizacije;
  }
  public String csvToString()
  {
    return super.csvToString()+","+nazivOrganizacije;
  }
}
