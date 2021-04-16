/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import enity.Article;
import enity.Contact;
import enity.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Win 10
 */
public class DAO {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    public Article getArticleIntroByType(String type){
        String query = "select * from Intros where Type=?";
        try {
            conn=new DBContext().getConnection();
            ps=conn.prepareStatement(query);
            ps.setString(1, type);
            rs=ps.executeQuery();
            while(rs.next()){
                return new Article(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
    public List<Article> getArticleInPageHasPageSizeInHome(int index){
        List<Article> list=new ArrayList<>();
        String query="select * from (\n" +
"                select *, ROW_NUMBER() over (order by id asc) as rownumber \n" +
"                from Artical) as Artical\n" +
"                where Artical.rownumber between ? and ?";
        try {
            conn=new DBContext().getConnection();
            ps=conn.prepareStatement(query);
            ps.setInt(1, index*6-5);
            ps.setInt(2, index*6);
            rs=ps.executeQuery();
            
            while(rs.next()){
               list.add(new Article(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)) );
            }
            
        } catch (Exception e) {
        }
        return list;
    }
    public List<Article> getArticleInPageHasPageSizeInSale(int index){
        List<Article> list=new ArrayList<>();
        String query="with T as (select *,ROW_NUMBER() over (order by id asc) as [row] from Artical)\n" +
"Select * from T where [row] between ? and ?";
        try {
             conn=new DBContext().getConnection();
            ps=conn.prepareStatement(query);
            ps.setInt(1, index*3-2);
            ps.setInt(2, index*3);
            rs=ps.executeQuery();
            while(rs.next()){
               list.add(new Article(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)) );
            }
            
        } catch (Exception e) {
        }
        return list;
    }
    
    public int countArticle(){
        String query = "select count (*) from Artical";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        
        return 0;
    }
    public Contact getContact(){
        String query="Select * from Info";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Contact(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public boolean insertMessage(Message m) {
        String sql = "Insert into [Message](Name, Email, Message)"
                +"values(?,?,?)";
        int check = 0;       
        try{
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, m.getName());
            ps.setString(2, m.getEmail());
            ps.setString(3, m.getMessage());
            check = ps.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex.getMessage().toString());
        }
        return check > 0; 
    }
    public Article getSingle(int id){
        String sql="Select * from Artical where id =?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
                return new Article(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public static void main(String[] args) {
        DAO dao=new DAO();
        Article a=dao.getArticleIntroByType("intro");
        List<Article> listH=dao.getArticleInPageHasPageSizeInHome(1);
        for (Article article : listH) {
            System.out.println(article);
        }
    }
}
