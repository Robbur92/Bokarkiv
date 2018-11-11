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
			JOptionPane.showMessageDialog(null, "Finner ikke str�mmen");
		}
		return true;
		//< Leser verdier fra fil og lagrer dem i de tilh�rende datafeltene. >
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
			JOptionPane.showMessageDialog(null, "Finner ikke str�mmen");
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
		//< Leser verdier fra fil og lagrer dem i de tilh�rende datafeltene. >
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
	private String fagomr�de;
	
	public Fagbok()
	{
	}

	public Fagbok( String f, String t, int sider, double p, String omr )
    {
		super( f, t, sider, p );
		fagomr�de = omr;
	}

	public boolean lesObjektFraFil( DataInputStream input ) throws IOException
	{
		super.lesObjektFraFil(input);
		fagomr�de = input.readUTF();
		return true;
		//< Leser verdier fra fil og lagrer dem i de tilh�rende datafeltene. >
	}

	public void skrivObjektTilFil( DataOutputStream output ) throws IOException
	{
		output.writeUTF("Fagbok");
		super.skrivObjektTilFil(output);
		output.writeUTF(fagomr�de);
	  //< Skriver datafeltenes verdier til fil. >
	}

	public String toString()
    {
		String s = super.toString();
		s += "; " + fagomr�de;
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
		//< Leser verdier fra fil og lagrer dem i de tilh�rende datafeltene. >
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
	private String m�lform;
	
	public NorskRoman()
	{
	}

	public NorskRoman( String f, String t,  int s, double p, String sj, String m )
    {
		super( f,t, s, p, sj );
		m�lform = m;
	}

	public boolean lesObjektFraFil( DataInputStream input ) throws IOException
	{
		super.lesObjektFraFil(input);
		m�lform = input.readUTF();
		return true;
		//< Leser verdier fra fil og lagrer dem i de tilh�rende datafeltene. >
	}

	public void skrivObjektTilFil( DataOutputStream output ) throws IOException
	{
		output.writeUTF("NorskRoman");
		super.skrivObjektTilFil(output);
		output.writeUTF(m�lform);
	  //< Skriver datafeltenes verdier til fil. >
	}

	public String toString()
    {
		String s = super.toString();
		s += ". " + m�lform;
		return s;
	}
}

///////////////////////////////////////////////////
class UtenlandskRoman extends Roman
{
	private String spr�k;
	
	public UtenlandskRoman()
	{
	}

	public UtenlandskRoman( String f, String t,  int s, double p, String sj, String sp )
    {
		super( f, t, s, p, sj );
		spr�k = sp;
	}

	public boolean lesObjektFraFil( DataInputStream input ) throws IOException
	{
		super.lesObjektFraFil(input);
		spr�k = input.readUTF();
		return true;
		//< Leser verdier fra fil og lagrer dem i de tilh�rende datafeltene. >
	}

	public void skrivObjektTilFil( DataOutputStream output ) throws IOException
	{
		output.writeUTF("Utenlandskroman");
		super.skrivObjektTilFil(output);
		output.writeUTF(spr�k);
	  //< Skriver datafeltenes verdier til fil. >
	}

	public String toString()
	{
		String s = super.toString();
		s += ". " + spr�k;
		return s;
	}
}
