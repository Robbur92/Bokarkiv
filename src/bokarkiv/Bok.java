package bokarkiv;

import java.io.*;
import javax.swing.*;

public abstract class Bok
{
	private String forfatter, tittel;
	private int sideantall;
	private double pris;
    Bok neste;
    
    public Bok()
    {
    }

	public Bok( String f, String t, int sider, double p )
    {
		forfatter = f;
		tittel = t;
		sideantall = sider;
		pris = p;
                neste = null;
	}

	public String getForfatter()
	{
		return forfatter;
	}


	public String getTittel()
    {
		return tittel;
	}

	public boolean lesObjektFraFil( DataInputStream input ) throws IOException
	{
		try
		{
			forfatter = input.readUTF();
			tittel = input.readUTF();
			sideantall = input.readInt();
			pris = input.readDouble();
		}
		catch( IOException s )
		{
			JOptionPane.showMessageDialog(null, "Finner ikke strømmen");
		}
		return true;
		//< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
	}

	public void skrivObjektTilFil( DataOutputStream output ) throws IOException
	{
		try
		{
			output.writeUTF(forfatter);
			output.writeUTF(tittel);
			output.writeInt(sideantall);
			output.writeDouble(pris);
		}
		catch( IOException s )
		{
			JOptionPane.showMessageDialog(null, "Finner ikke strømmen");
		}
	  //< Skriver datafeltenes verdier til fil. >
	}

	public String toString()
    {
		String s = forfatter + "; ";
		s += tittel + "; ";
		s += sideantall + " s., ";
		s += "kr. " + pris;
		return s;
	}
}
///////////////////////////////////////////////////////////////
class Skolebok extends Bok
{
	private int klassetrinn;
	private String skolefag;
	
	public Skolebok()
	{
	}

	public Skolebok( String f, String t,int sider, double p, int kt, String fag )
    {
		super( f, t, sider, p );
		klassetrinn = kt;
		skolefag = fag;
	}

	public boolean lesObjektFraFil( DataInputStream input ) throws IOException
	{
		super.lesObjektFraFil(input);
		klassetrinn = input.readInt();
		skolefag = input.readUTF();
		return true;
		//< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
	}

	public void skrivObjektTilFil( DataOutputStream output ) throws IOException
	{
		output.writeUTF("Skolebok");
		super.skrivObjektTilFil(output);
		output.writeInt(klassetrinn);
		output.writeUTF(skolefag);
	  //< Skriver datafeltenes verdier til fil. >
	}

	public String toString()
    {
		String s = super.toString();
		s += "; trinn: " + klassetrinn;
		s += ", " + skolefag;
		return s;
	}
}
//////////////////////////////////////////////////////////////
class Fagbok extends Bok
{
	private String fagområde;
	
	public Fagbok()
	{
	}

	public Fagbok( String f, String t, int sider, double p, String omr )
    {
		super( f, t, sider, p );
		fagområde = omr;
	}

	public boolean lesObjektFraFil( DataInputStream input ) throws IOException
	{
		super.lesObjektFraFil(input);
		fagområde = input.readUTF();
		return true;
		//< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
	}

	public void skrivObjektTilFil( DataOutputStream output ) throws IOException
	{
		output.writeUTF("Fagbok");
		super.skrivObjektTilFil(output);
		output.writeUTF(fagområde);
	  //< Skriver datafeltenes verdier til fil. >
	}

	public String toString()
    {
		String s = super.toString();
		s += "; " + fagområde;
		return s;
	}
}
////////////////////////////////////////////////////
abstract class Roman extends Bok
{
	protected String sjanger;
	
	public Roman()
	{
	}

	protected Roman( String f, String t, int sider, double p, String s )
    {
		super( f, t, sider, p );
		sjanger = s;
	}

	public boolean lesObjektFraFil( DataInputStream input ) throws IOException
	{
		super.lesObjektFraFil(input);
		sjanger = input.readUTF();
		return true;
		//< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
	}

	public void skrivObjektTilFil( DataOutputStream output ) throws IOException
	{
		super.skrivObjektTilFil(output);
		output.writeUTF(sjanger);
	  //< Skriver datafeltenes verdier til fil. >
	}

	public String toString()
    {
		String s = super.toString();
		s += ". Sjanger: " + sjanger;
		return s;
	}
}

///////////////////////////////////////////////////
class NorskRoman extends Roman
{
	private String målform;
	
	public NorskRoman()
	{
	}

	public NorskRoman( String f, String t,  int s, double p, String sj, String m )
    {
		super( f,t, s, p, sj );
		målform = m;
	}

	public boolean lesObjektFraFil( DataInputStream input ) throws IOException
	{
		super.lesObjektFraFil(input);
		målform = input.readUTF();
		return true;
		//< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
	}

	public void skrivObjektTilFil( DataOutputStream output ) throws IOException
	{
		output.writeUTF("NorskRoman");
		super.skrivObjektTilFil(output);
		output.writeUTF(målform);
	  //< Skriver datafeltenes verdier til fil. >
	}

	public String toString()
    {
		String s = super.toString();
		s += ". " + målform;
		return s;
	}
}

///////////////////////////////////////////////////
class UtenlandskRoman extends Roman
{
	private String språk;
	
	public UtenlandskRoman()
	{
	}

	public UtenlandskRoman( String f, String t,  int s, double p, String sj, String sp )
    {
		super( f, t, s, p, sj );
		språk = sp;
	}

	public boolean lesObjektFraFil( DataInputStream input ) throws IOException
	{
		super.lesObjektFraFil(input);
		språk = input.readUTF();
		return true;
		//< Leser verdier fra fil og lagrer dem i de tilhørende datafeltene. >
	}

	public void skrivObjektTilFil( DataOutputStream output ) throws IOException
	{
		output.writeUTF("Utenlandskroman");
		super.skrivObjektTilFil(output);
		output.writeUTF(språk);
	  //< Skriver datafeltenes verdier til fil. >
	}

	public String toString()
	{
		String s = super.toString();
		s += ". " + språk;
		return s;
	}
}
