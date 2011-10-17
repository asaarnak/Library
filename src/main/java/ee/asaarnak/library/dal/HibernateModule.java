package ee.asaarnak.library.dal;

import org.apache.tapestry5.beanvalidator.BeanValidatorConfigurer;
import org.apache.tapestry5.hibernate.HibernateTransactionAdvisor;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Match;

/**
 * This class should contain contribution to data stuff (hibernate
 * configuration, beanvalidators...)
 */
public class HibernateModule {
  /**
   * Little aspect for calling commit on method with @CommitAfter annotation.
   * Works with interfaces.
   */
  @Match("*DAO")
  public static void adviseTransactions(HibernateTransactionAdvisor advisor, MethodAdviceReceiver receiver) {
    advisor.addTransactionCommitAdvice(receiver);
  }

  public static void bind(ServiceBinder binder) {
    binder.bind(CrudServiceDAO.class, HibernateCrudServiceDAO.class);
  }

  public static void contributeBeanValidatorSource(OrderedConfiguration<BeanValidatorConfigurer> configuration) {
    configuration.add("LibraryConfigurer", new BeanValidatorConfigurer() {
      @Override
      public void configure(javax.validation.Configuration<?> configuration) {
        configuration.ignoreXmlConfiguration();
      }
    });
  }
}
