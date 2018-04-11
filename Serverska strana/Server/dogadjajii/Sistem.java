package dogadjajii;
import osobe.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class Sistem
{
  
  
  public Sistem()
  {
    super();
  }
  
  public static <T> void serijalizacijaPodataka(T lista,String ime)
  {
    try{
      ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(".//..//serijalizacija/"+File.separator+ime+".ser"));
      oos.writeObject(lista);
      oos.close();
    }catch( Exception e)
    { e.printStackTrace();
    }
  }
  @SuppressWarnings("unchecked") 
  public static <T> T deserijalizacijaPodataka(String ime)
  {
    T lista=null;
    try
    {
      ObjectInputStream ois=new ObjectInputStream(new FileInputStream(".//..//serijalizacija/"+File.separator+ime+".ser"));
      lista=(T)ois.readObject();
      ois.close();
    }catch(Exception e)
    {e.printStackTrace();
    }
    return lista;
  }
  @SuppressWarnings("unchecked") 
  public void obrisiDogadjaj(String dog)
  {
    ArrayList<Dogadjaj> dogadjaji=Sistem.deserijalizacijaPodataka("Dogadjaji");
    for(int i=0;i<dogadjaji.size();i++)
    {
      if(dogadjaji.get(i).getNaziv().equals(dog))
      {
        dogadjaji.remove(i);
      }
    }
    Collections.sort(dogadjaji);
    serijalizacijaPodataka(dogadjaji,"Dogadjaji");
  }
  
//  public <T> void novaOsoba(T a,String vrsta)
//  {
//    Sistem.osobe.get(vrsta).add(a);
//  }
  
  
  
  
}