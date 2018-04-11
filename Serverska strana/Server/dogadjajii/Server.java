package dogadjajii;
import osobe.*;
import dogadjajii.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Server
{
  
  public static final int PORT=9000;
  public static int counter=0;
  
  @SuppressWarnings("unchecked") 
  public static void ucitajPodatke(String a,ArrayList<? extends Osoba> lista)
  {
    try
    {
      BufferedReader in = new BufferedReader(new FileReader(a+".txt"));
      String s;
      while ((s = in.readLine()) != null) {
        String[] info=s.split(",");
        if("Posjetioci".equals(a))
        {
          ArrayList<Posjetilac> listaa=(ArrayList<Posjetilac>)lista;
          listaa.add(new Posjetilac(info[3],info[4],info[0],info[1],info[2]));
        }
        else if("Predavaci".equals(a))
        {
          ArrayList<Predavac> listaa=(ArrayList<Predavac>)lista;
          listaa.add(new Predavac(info[0],info[1]));
        }  
        
        else if("Organizatori".equals(a))
        {
          Napomena nap=new Napomena("napomena 1");
        ArrayList<Napomena> napomene=new ArrayList<Napomena>();
        napomene.add(nap);
          ArrayList<Organizator> listaa=(ArrayList<Organizator>)lista;
          listaa.add(new Organizator(info[0],info[1],info[2],info[3],napomene));
        } 
        
        else if("Ucesnici".equals(a))
        {
          ArrayList<Ucesnik> listaa=(ArrayList<Ucesnik>)lista;
          listaa.add(new Ucesnik(info[0],info[1],info[2]));
        }
        
        else if("Izvodjaci".equals(a))
        {
          ArrayList<Izvodjac> listaa=(ArrayList<Izvodjac>)lista;
          listaa.add(new Izvodjac(info[0],info[1]));
        }
      }
    }catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  
  
  @SuppressWarnings("unchecked")
  public static void init()
  {
    ArrayList<Dogadjaj> dogadjaji=new ArrayList<Dogadjaj>();
    ArrayList<Organizator> organizatori=new ArrayList<Organizator>();
    ArrayList<Posjetilac> posjetioci=new ArrayList<Posjetilac>();
    ArrayList<Ucesnik> ucesnici=new ArrayList<Ucesnik>();
    ArrayList<Predavac> predavaci=new ArrayList<Predavac>();
    ArrayList<Izvodjac> izvodjaci=new ArrayList<Izvodjac>();
    LinkedHashMap<String,ArrayList<? extends Osoba>> osobe=new LinkedHashMap<String,ArrayList<? extends Osoba>>();
    osobe.put("Ucesnici",ucesnici);
    osobe.put("Predavaci",predavaci);
    osobe.put("Organizatori",organizatori);
    osobe.put("Posjetioci",posjetioci);
    osobe.put("Izvodjaci",izvodjaci);
    for(String key:osobe.keySet())
      ucitajPodatke(key,osobe.get(key));
    Sistem.serijalizacijaPodataka(posjetioci,"Posjetioci");
    Sistem.serijalizacijaPodataka(ucesnici,"Ucesnici");
    Sistem.serijalizacijaPodataka(organizatori,"Organizatori");
    Sistem.serijalizacijaPodataka(izvodjaci,"Izvodjaci");
    Sistem.serijalizacijaPodataka(predavaci,"Predavaci");
    Sistem.serijalizacijaPodataka(osobe,"Osobe");
    
    
    File fajl=new File(".\\..\\Podaci\\globalnoZagrijavanje.jpg");
    ArrayList<File> f1=new ArrayList<File>();
    f1.add(fajl);
    Predavanja pr=new Predavanja("Globalno zagrijavanje","2017-03-28","2017-03-28","19 : 00","22 : 00","Predavanje na temu Globalno Zagrijavanje",organizatori.get(0),ucesnici,f1,"Globalno zagrijavanje",predavaci.get(0),"Sadrzaj o predavanju.............");
    dogadjaji.add(pr);
    File fajll=new File(".\\..\\Podaci\\orloviRanoLete.jpg");
    ArrayList<File> f2=new ArrayList<File>();
    f2.add(fajll);
    Promocija p=new Promocija("Promocija Knjige","2017-02-10","2017-02-10","13 : 00","16 : 00","Promocija knjige Ive Andrica",organizatori.get(1),ucesnici,f2,"Orlovi Rano Lete");
    dogadjaji.add(p);
    
    Collections.sort(dogadjaji);
    Sistem.serijalizacijaPodataka(dogadjaji,"Dogadjaji");
  }
  public static void main(String[] args)
  {
   // init();
    try{
      
      ServerSocket ss=new ServerSocket(PORT);
      System.out.println("Pokrenut je glavni server...");
      while(true)
      {
        Socket sock=ss.accept();
        System.out.println("Prihvacen je klijent: "+(++counter));
        new ServerThread(sock,counter);}
    }
    catch(SocketException se){
      System.out.println("Klijent se odjavio");
    }catch( Exception e)
    {
      e.printStackTrace();
    }}}