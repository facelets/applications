package demo.application.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import demo.application.model.entity.Test;

@ManagedBean(name="testView")
@ViewScoped
public class TestView extends AbstractFacesBean {
	private static final long serialVersionUID = 1L;

	 public TestView() {
		testPersistenceManager();
	}
	
	public void testPersistenceManager() {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("web-application-pu");
			List<Test> list = emf.createEntityManager().createQuery("Select o from Test o", Test.class).getResultList();
			for (Test test : list) {
				System.out.println(test.getId() + "\t" +  test.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	public void testJDBCConnection() {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/testDB");
			Connection conn = ds.getConnection();
			ResultSet rset = conn.createStatement().executeQuery("SELECT id, value FROM t_test");
			while (rset.next()) {
				System.out.println(rset.getInt(1) + "\t" + rset.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
