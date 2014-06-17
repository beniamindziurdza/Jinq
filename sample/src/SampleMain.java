

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.orm.stream.JinqStream;

import com.example.jinq.sample.jpa.entities.Customer;
import com.example.jinq.sample.jpa.entities.Sale;

import ch.epfl.labos.iu.orm.Pair;

public class SampleMain
{
   static EntityManagerFactory entityManagerFactory;
   static JinqJPAStreamProvider streams;

   EntityManager em;

   public static void main(String[] args)
   {
      entityManagerFactory = Persistence.createEntityManagerFactory("JPATest");
      streams = new JinqJPAStreamProvider(entityManagerFactory);
      EntityManager em = entityManagerFactory.createEntityManager();
      new CreateJpaDb(em).createDatabase();
      em.close();
      
      new SampleMain().go();
   }

   public void go()
   {
      em = entityManagerFactory.createEntityManager();
      JinqStream<Customer> customers = streams.streamAll(em, Customer.class);
      List<Customer> customerList = customers.toList();
      for (Customer c: customerList)
         System.out.println(c.getName());
      em.close();
   }
}