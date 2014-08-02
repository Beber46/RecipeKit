package fr.beber.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BDD  extends SQLiteOpenHelper {
	
	//Version de la base de donnees
	private static final int DATABASE_VERSION = 1;
	
	//Nom de la base
	private static final String BASE_NAME = "recipekit.db";
	
	/**
	 * Création de la table Produit
	 */
	public static final String TN_PRODUIT = "Produit";//TN = Table Name
	public static final String PRODUIT_COLUMN_ID = "ID";
	public static final int PRODUIT_NUM_ID = 0;
	public static final String PRODUIT_COLUMN_NAME = "NAME";
	public static final int PRODUIT_NUM_NAME = 1;
	
	private static final String REQUETE_CREATION_PRODUIT = "CREATE TABLE "+TN_PRODUIT+" " +
			"("+PRODUIT_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+PRODUIT_COLUMN_NAME+" TEXT); ";
	
	/**
	 * Création de la table Recette
	 */
	public static final String TN_RECETTE = "RECETTE";//TN = Table Name
	public static final String RECETTE_COLUMN_ID = "ID";
	public static final int RECETTE_NUM_ID = 0;
	public static final String RECETTE_COLUMN_NAME = "NAME";
	public static final int RECETTE_NUM_NAME = 1;
	
	private static final String REQUETE_CREATION_RECETTE = "CREATE TABLE "+TN_RECETTE+" " +
			"("+RECETTE_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+RECETTE_COLUMN_NAME+" TEXT); ";

	/**
	 * Création de la table Composition
	 */
	public static final String TN_COMPOSITION = "COMPOSITION";//TN = Table Name
	public static final String COMPOSITION_COLUMN_ID = "ID";
	public static final int COMPOSITION_NUM_ID = 0;
	public static final String COMPOSITION_COLUMN_ID_PRODUIT = "ID_PRODUIT";
	public static final int COMPOSITION_NUM_ID_PRODUIT = 1;
	public static final String COMPOSITION_COLUMN_ID_RECETTE = "ID_RECETTE";
	public static final int COMPOSITION_NUM_ID_RECETTE = 2;
	public static final String COMPOSITION_COLUMN_QUANTITE = "QUANTITE";
	public static final int COMPOSITION_NUM_QUANTITE = 3;
	public static final String COMPOSITION_COLUMN_ID_UNIT = "ID_UNIT";
	public static final int COMPOSITION_NUM_ID_UNIT = 4;
	
	private static final String REQUETE_CREATION_COMPOSITION = "CREATE TABLE "+TN_COMPOSITION+" " +
			"("+COMPOSITION_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+COMPOSITION_COLUMN_ID_PRODUIT+" INTEGER NOT NULL, "
			+COMPOSITION_COLUMN_ID_RECETTE+" INTEGER NOT NULL, "
			+COMPOSITION_COLUMN_QUANTITE+" INTEGER NOT NULL, "
			+COMPOSITION_COLUMN_ID_UNIT+" INTEGER NOT NULL "
					+"); ";
	
	/**
	 * Création de la table Recette
	 */
	public static final String TN_UNIT = "UNIT";//TN = Table Name
	public static final String UNIT_COLUMN_ID = "ID";
	public static final int UNIT_NUM_ID = 0;
	public static final String UNIT_COLUMN_NAME = "NAME";
	public static final int UNIT_NUM_NAME = 1;
	
	private static final String REQUETE_CREATION_UNIT = "CREATE TABLE "+TN_UNIT+" " +
			"("+UNIT_COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
			+UNIT_COLUMN_NAME+" TEXT); ";
	
	/**
	 * Constructeur provenant de l'heritage
	 * @param context
	 * @param factory
	 */
	public BDD(Context context,CursorFactory factory) {
		super(context, BASE_NAME, factory, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Creation de la table produit
		db.execSQL(REQUETE_CREATION_PRODUIT);

		//Creation de la table recette
		db.execSQL(REQUETE_CREATION_RECETTE);
		
		//Création de la table composition
		db.execSQL(REQUETE_CREATION_COMPOSITION);

		//Création de la table unit
		db.execSQL(REQUETE_CREATION_UNIT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Lorsque l'on change le numero de version de la base on supprime la
	    // table puis on la recree
	    if (newVersion > DATABASE_VERSION) {
	        db.execSQL("DROP TABLE " + TN_PRODUIT + ";");
	        onCreate(db);
	    }
	}

}
