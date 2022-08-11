package threadManager;

import util.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RichiestaThreadDAO {

    private static Connection con=null;
    private static PreparedStatement statement=null;
    private static ResultSet set=null;
    private static String addReq;
    private static String viewReq;
    private static String deleteReq;


    public  boolean addThreadRequest(RichiestaThread req) throws SQLException
    {

        addReq= "INSERT INTO richiesta_thread(utente,titolo,tipo,testo,id_gioco) values(?,?,?,?,?)";
        boolean flag=false;

        if(req==null)
            throw new SQLException();
        else {
            try
            {
                con= ConnectionPool.getConnection();
                statement=con.prepareStatement(addReq);
                statement.setString(1,req.getUtente());
                statement.setString(2,req.getTitolo());
                statement.setString(3,req.getTipo());
                statement.setString(4,req.getTesto());
                statement.setInt(5,req.getId_gioco());
                flag=statement.executeUpdate()>0;
                con.commit();
            }
            finally
            {
                try
                {
                    if(statement!=null)
                        statement.close();
                }
                finally
                {

                }
            }
            return flag;
        }
    }


    public RichiestaThread viewRequestById(int id) throws SQLException
    {
        viewReq="SELECT * FROM richiesta_thread WHERE id=?";
        ArrayList<RichiestaThread> richieste=new ArrayList<RichiestaThread>();

        try
        {
            con=ConnectionPool.getConnection();
            statement=con.prepareStatement(viewReq);
            statement.setInt(1, id);
            set=statement.executeQuery();
            while(set.next())
            {
                RichiestaThread req=new RichiestaThread();
                req.setId_richiesta(set.getInt(1));
                req.setUtente(set.getString(2));
                req.setTitolo(set.getString(3));
                req.setTipo(set.getString(4));
                req.setTesto(set.getString(5));
                req.setId_gioco(set.getInt(6));
                richieste.add(req);
            }
        }
        finally
        {
            try
            {
                if(statement!=null)
                    statement.close();
            }
            finally
            {

            }
        }
        if(richieste.size()!=0) {
            return richieste.get(0);
        } else {
            return null;
        }
    }


    public ArrayList<RichiestaThread> getAllRequests() throws SQLException
    {
        viewReq="SELECT * FROM richiesta_thread";
        ArrayList<RichiestaThread> richieste=new ArrayList<RichiestaThread>();

        try
        {
            con=ConnectionPool.getConnection();
            statement=con.prepareStatement(viewReq);;
            set=statement.executeQuery();
            while(set.next())
            {
                RichiestaThread req=new RichiestaThread();
                req.setId_richiesta(set.getInt(1));
                req.setUtente(set.getString(2));
                req.setTitolo(set.getString(3));
                req.setTipo(set.getString(4));
                req.setTesto(set.getString(5));
                req.setId_gioco(set.getInt(6));
                richieste.add(req);
            }
        }
        finally
        {
            try
            {
                if(statement!=null)
                    statement.close();
            }
            finally
            {

            }
        }
        return richieste;
    }


    public  boolean deleteThreadRequest(int id) throws SQLException
    {
        boolean flag=false;
        try
        {
            deleteReq="DELETE FROM richiesta_thread WHERE id=?";
            con=ConnectionPool.getConnection();
            statement=con.prepareStatement(deleteReq);
            statement.setInt(1,id);
            flag=statement.executeUpdate()>0;
            con.commit();
        }
        finally
        {
            try
            {
                if(statement!=null)
                    statement.close();
            }
            finally
            {

            }
        }
        return flag;
    }
}
