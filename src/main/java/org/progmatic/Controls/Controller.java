package org.progmatic.Controls;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.progmatic.model.Author;
import org.progmatic.model.Book;
import org.progmatic.model.Store;
import java.util.List;


public class Controller implements AutoCloseable {
    private HibernateContext model = new HibernateContext();


    public void Add_auto_data() {
        Session s = model.getSession();
        Transaction tx = s.beginTransaction();

        try {

            Author a1=new Author();
            a1.setName("Konkei Horikoshi");
            s.persist(a1);

            Book b1 = new Book();
            b1.setTitle("My hero academia");
            b1.setISBN("1411514");
            b1.setAuthor(a1);
            s.persist(b1);



            Store s1 =new Store();
            s1.setBook(List.of(b1));
            s1.setName("Manga_club");
            s1.setAvailable(true);
            s.persist(s1);



            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }
    }

    public void Modify_Book(Long Id,String isb,String title){
        Session session= model.getSession();
        Transaction tx=session.beginTransaction();
        Book book=session.find(Book.class,Id);


        book.setISBN(isb);
        book.setTitle(title);
        session.persist(book);
        tx.commit();


    };

    public void  Search_Book_Title(String name) {
        Session session= model.getSession();
        Transaction tx= session.beginTransaction();
        String hql="FROM Book WHERE Title=:x";
        Query query=session.createQuery(hql,Book.class).setParameter("x",name);

        List<Book> results  =query.getResultList();

        tx.commit();
        System.out.println(results);
    }



public void Add_Author(String name){

    Author a = new Author();

    a.setName(name);

    Session session = model.getSession();
    session.getTransaction().begin();
    session.persist(a);
    session.flush();
    session.getTransaction().commit();
    System.out.println("Author id = " + a.getId());
}
public void  Modify_Author(Long Id,String name){
    Session session= model.getSession();
    Transaction tx= session.beginTransaction();
    Author author=session.find(Author.class,Id);

    author.setName(name);
    session.persist(author);
    tx.commit();
}
public void Delete_Author(Long pid){
    Session session = model.getSession();
    Transaction tx = null;
    try {

        tx = session.beginTransaction();

        Author a = session.find(Author.class, pid);
        session.remove(a);

        tx.commit();
    } catch (Exception e) {
        if (tx != null) {
            tx.rollback();
        }
    }

}



public void Add_Store(boolean avaibale, String name){

    Store s=new Store();

    s.setAvailable(avaibale);
    s.setName(name);
    Session session= model.getSession();
    session.getTransaction().begin();
    session.persist(s);
    session.flush();

    session.getTransaction().commit();
    System.out.println("Store Id ="+s.getId());

}
public void Modify_Store(Long Id,boolean avaiable,String name){
    Session session= model.getSession();
    Transaction tx= session.beginTransaction();
    Store store=session.find(Store.class,Id);

    store.setAvailable(avaiable);
    store.setName(name);
    tx.commit();
}




    @Override
    public void close() throws Exception {
        model.close();
    }
}
