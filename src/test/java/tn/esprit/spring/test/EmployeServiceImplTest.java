package tn.esprit.spring.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tn.esprit.spring.entity.Employe;
import tn.esprit.spring.entity.Role;
import tn.esprit.spring.repository.IEmployeRepository;
import tn.esprit.spring.service.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeServiceImplTest {
	
	private static final long DEFAULT_TIMEOUT = 10000;
	private static final Logger l = LogManager.getLogger(EmployeServiceImplTest.class);

	@Autowired
	IEmployeService es;
	
	@Autowired
	IEmployeRepository er;
	
	//test de la methode ajout
	@Test(timeout = DEFAULT_TIMEOUT)
	public void testaddemploye() {
		Employe employe = new Employe("Mohamed","Guebli","mohamed.guebli1@esprit.tn",true,Role.INGENIEUR,"mohamed");
	es.ajouterEmploye(employe);
	assertNotNull(employe.getIdEmploye());
	l.info("Employe is added with success ");
	er.deleteById(employe.getIdEmploye());
	l.info("Employe is deleted with success ");
	}
	

	
	//test nbrs lignes dans la base il doit etre sup à 0
	@Test
	public void testListEmploye() {
	List<Employe> e = (List<Employe>) er.findAll();
	assertThat(e).size().isPositive();
		if (e.isEmpty()){
			l.info("Employes list is empty: ");
		}else{
			l.info("Employes list");
		}
	}
	
	//test meth count NOTE:"le retour de la methode ne doit pas etre null".
	@Test
	public void testcountEmploye() {
	List<Employe> e = (List<Employe>) er.findAll();
	assertNotNull(e);
	}
	
	//test nom n'est pas null
	@Test
	public void testEmailAndPasswordEmploye(){
		Employe emp = er.getEmployeByEmailAndPassword("mohamed.guebli1@esprit.tn", "mohamed");
		assertNotNull(emp.getNomEmploye());
	}
	
	// test du suppression de l'employee
	@Test(timeout = 5000)
	public void testDeleteEmploye() {
		Employe emp = new Employe();
		assertNotNull(emp);
		er.deleteById((long) 6);
		l.info("deleted with success" );
	}

}
