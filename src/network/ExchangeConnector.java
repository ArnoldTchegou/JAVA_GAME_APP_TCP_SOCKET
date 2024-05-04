package network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pierrecharbit
 */
public class ExchangeConnector {
	/** l'IP du serveur. */
	String ip;
	/** le port sur lequel le serveur attend la connextion. */
	int port;
	
	
	
	//les flux pour communiquer avec le serveur
	private DataInputStream in;
	private DataOutputStream out;
	//les flux pour convertir l'objet en donnees "pures" de type byte[] 
	private ByteArrayOutputStream baos;
	private ObjectOutputStream oos;
	private ByteArrayInputStream bais;
	private ObjectInputStream ois;

	/**
	 * une seed donnée par le serveur lors de initConnect() pour permettre aux
	 * clients de faire les memes tirages aléatoires.
	 */
	public long seed;
	/**
	 * un entier 0 ou 1 pour connaitre son coté dans le jeu, donnée par le
	 * serveur lors de initConnect().
	 */
	public int side;

	
	/**
	 * constructeur sur un port donné et une IP donnée
	 *
	 * @param ip l'IP du GameServer à contacter
	 * @param port le port sur lequel le GameServer attend les connexions entrantes.
	 */
	public ExchangeConnector(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	
	/**
	 * constructeur sur un port donné en localhots
	 *
	 * @param port
	 */
	public ExchangeConnector(int port) {
		this("localhost",port);
	}

	/**
	 * constructeur en localhost sur le port 4242.
	 */
	public ExchangeConnector() {
		this(4242); //defaul
	}

	/**
	 * initialise la connection avec un ExchangeGameServer.
	 * Au passage le serveur initialise le champ seed et le champ side.
	 * Le champ seed permettra au client de fabriquer un objet Random, c'est donc le meme qui est envoyé aux deux clients.
	 * Le champ side est envoyé a chaque client, l'un reçoit 0, et l'autre reçoit 1 (ça peut servir dans certaines situations)
	 * @throws java.io.IOException si Serveur injoignable
	 */
	public void initConnect() throws IOException{
		
			Socket sockS = new Socket(ip, port);
			System.out.println("Connector : Connection etablie");

			out = new DataOutputStream(sockS.getOutputStream());
			in = new DataInputStream(sockS.getInputStream());

			this.seed = (Long) in.readLong();
			System.out.println("Seed recue " + seed);
			out.write(0);
			this.side = (Integer) in.readInt();
			System.out.println("side recu : " + side);
			out.write(0);

	}

	/**
	 * Permet d'echanger une donnée Objet avec un client connecté a la meme
	 * partie sur le GameServer.
	 *
	 * @param <E> le type de la donnée Objet
	 * @param e la donnée a envoyer
	 * @return une donnée du meme type que @param e envoyée par l'autre client.
	 */
	public <E> E exchangeObj(E e) {
		send(e);
		return ((E) receive());
	}

	void send(Object obj) {

		try {
			baos = new ByteArrayOutputStream();
			try {
				oos = new ObjectOutputStream(baos);
			}
			catch (IOException ex) {
				Logger.getLogger(ExchangeConnector.class.getName()).log(Level.SEVERE, null, ex);
			}

			oos.writeObject(obj);
			oos.flush();
			oos.close();
			byte[] data = baos.toByteArray();

			out.writeInt(data.length);
			out.write(data);

			out.flush();
		}
		catch (IOException ex) {
			Logger.getLogger(ExchangeConnector.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	Object receive() {
		try {
			int n = in.readInt();
			byte[] data = new byte[n];
			in.read(data);

			bais = new ByteArrayInputStream(data);
			ois = new ObjectInputStream(bais);
			Object obj = ois.readObject();
			return obj;
		}
		catch (IOException | ClassNotFoundException ex) {
			Logger.getLogger(ExchangeConnector.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
