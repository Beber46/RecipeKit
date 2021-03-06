package fr.beber.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Classe BDD permettant des créer et mettre à jour la base de données. Elle permet également d'accéder aux constantes
 * propres aux tables.
 */
public class BDD extends SQLiteOpenHelper {

    /**
     * **************************** Création de la table Produit
     */
    public static final String TN_PRODUIT = "PRODUIT";
    public static final String PRODUIT_COLUMN_ID = "_id";
    public static final int PRODUIT_NUM_ID = 0;
    public static final String PRODUIT_COLUMN_NAME = "NAME";
    /**
     * Permet de construire la requête pour créer la table produit
     */
    private static final String REQUETE_CREATION_PRODUIT = "CREATE TABLE " + TN_PRODUIT + " " +
            "(" + PRODUIT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PRODUIT_COLUMN_NAME + " TEXT); ";
    public static final int PRODUIT_NUM_NAME = 1;
    /**
     * **************************** Création de la table Recette
     */
    public static final String TN_RECETTE = "RECETTE";
    public static final String RECETTE_COLUMN_ID = "_id";
    public static final int RECETTE_NUM_ID = 0;
    public static final String RECETTE_COLUMN_NAME = "NAME";
    public static final int RECETTE_NUM_NAME = 1;
    public static final String RECETTE_COLUMN_PREPARATION = "PREPARATION";
    public static final int RECETTE_NUM_PREPARATION = 2;
    public static final String RECETTE_COLUMN_TEMPS_PREPARATION = "TEMPS_PREPARATION";
    public static final int RECETTE_NUM_TEMPS_PREPARATION = 3;
    public static final String RECETTE_COLUMN_TEMPS_CUISSON = "TEMPS_CUISSON";
    public static final int RECETTE_NUM_TEMPS_CUISSON = 4;
    public static final String RECETTE_COLUMN_NOTE = "NOTE";
    public static final int RECETTE_NUM_NOTE = 5;
    public static final String RECETTE_COLUMN_NB_PERSONNE = "NB_PERSONNE";
    /**
     * Permet de construire la requête pour créer la table recette
     */
    private static final String REQUETE_CREATION_RECETTE = "CREATE TABLE " + TN_RECETTE + " " +
            "(" + RECETTE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + RECETTE_COLUMN_NAME + " TEXT NOT NULL, "
            + RECETTE_COLUMN_PREPARATION + " TEXT NOT NULL, "
            + RECETTE_COLUMN_TEMPS_PREPARATION + " INTEGER NOT NULL, "
            + RECETTE_COLUMN_TEMPS_CUISSON + " INTEGER, "
            + RECETTE_COLUMN_NOTE + " FLOAT, "
            + RECETTE_COLUMN_NB_PERSONNE + " INTEGER "
            + "); ";
    public static final int RECETTE_NUM_NB_PERSONNE = 6;
    /**
     * **************************** Création de la table Recette
     */
    public static final String TN_UNIT = "UNIT";
    public static final String UNIT_COLUMN_ID = "_id";
    public static final int UNIT_NUM_ID = 0;
    public static final String UNIT_COLUMN_NAME = "NAME";
    public static final int UNIT_NUM_NAME = 1;
    public static final String UNIT_COLUMN_ABREVIATION = "ABREVIATION";
    /**
     * Permet de construire la requête pour créer la table unité
     */
    private static final String REQUETE_CREATION_UNIT = "CREATE TABLE " + TN_UNIT + " " +
            "(" + UNIT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UNIT_COLUMN_ABREVIATION + " TEXT, "
            + UNIT_COLUMN_NAME + " TEXT); ";
    public static final int UNIT_NUM_ABREVIATION = 2;
    /**
     * **************************** Création de la table Composition
     */
    public static final String TN_COMPOSITION = "COMPOSITION";
    public static final String COMPOSITION_COLUMN_ID = "_id";
    public static final int COMPOSITION_NUM_ID = 0;
    public static final String COMPOSITION_COLUMN_ID_PRODUIT = "ID_PRODUIT";
    public static final int COMPOSITION_NUM_ID_PRODUIT = 1;
    public static final String COMPOSITION_COLUMN_ID_RECETTE = "ID_RECETTE";
    public static final int COMPOSITION_NUM_ID_RECETTE = 2;
    public static final String COMPOSITION_COLUMN_QUANTITE = "QUANTITE";
    public static final int COMPOSITION_NUM_QUANTITE = 3;
    public static final String COMPOSITION_COLUMN_ID_UNIT = "ID_UNIT";
    /**
     * Permet de construire la requête pour créer la table composition
     */
    private static final String REQUETE_CREATION_COMPOSITION = "CREATE TABLE " + TN_COMPOSITION + " " +
            "(" + COMPOSITION_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COMPOSITION_COLUMN_ID_PRODUIT + " INTEGER NOT NULL, "
            + COMPOSITION_COLUMN_ID_RECETTE + " INTEGER NOT NULL, "
            + COMPOSITION_COLUMN_QUANTITE + " FLOAT, "
            + COMPOSITION_COLUMN_ID_UNIT + " INTEGER,"
            + " FOREIGN KEY (" + COMPOSITION_COLUMN_ID_PRODUIT + ") REFERENCES " + TN_PRODUIT + " (" + PRODUIT_COLUMN_ID + "),"
            + " FOREIGN KEY (" + COMPOSITION_COLUMN_ID_RECETTE + ") REFERENCES " + TN_RECETTE + " (" + RECETTE_COLUMN_ID + "),"
            + " FOREIGN KEY (" + COMPOSITION_COLUMN_ID_UNIT + ") REFERENCES " + TN_UNIT + " (" + UNIT_COLUMN_ID + ")"
            + "); ";
    public static final int COMPOSITION_NUM_ID_UNIT = 4;
    /**
     * Version de la base de données
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Nom du schéma
     */
    private static final String BASE_NAME = "recipekit.db";

    /**
     * Constructeur provenant de l'heritage
     *
     * @param context Le contexte courant.
     * @param factory Définit le factory à utiliser.
     */
    public BDD(final Context context, final CursorFactory factory) {
        super(context, BASE_NAME, factory, DATABASE_VERSION);
    }

    /**
     * Constructeur provenant de l'heritage
     *
     * @param context Le contexte courant.
     */
    public BDD(final Context context) {
        this(context, null);
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(REQUETE_CREATION_PRODUIT);

        sqLiteDatabase.execSQL(REQUETE_CREATION_RECETTE);

        sqLiteDatabase.execSQL(REQUETE_CREATION_UNIT);

        sqLiteDatabase.execSQL(REQUETE_CREATION_COMPOSITION);

    }

    /**
     * Lorsque l'on change le numero de version de la base on supprime la table puis on la recree.
     *
     * @param sqLiteDatabase Nécessaire pour la méthode parente.
     * @param oldVersion     Ancienne version de la base de données.
     * @param newVersion     Nouvelle version de la base de données.
     */
    @Override
    public void onUpgrade(final SQLiteDatabase sqLiteDatabase, final int oldVersion, final int newVersion) {

        if (newVersion > DATABASE_VERSION) {
            Log.w(BDD.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_COMPOSITION + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_PRODUIT + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_RECETTE + ";");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TN_UNIT + ";");
            this.onCreate(sqLiteDatabase);
        }
    }

}
