package pe.gob.sunat.recurso2.documentacion.expvirtual.util;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.transaction.TransactionConfig;
import com.ibatis.sqlmap.engine.transaction.TransactionManager;

@SuppressWarnings("deprecation")
public class SqlMapClientImplFactoryBean extends SqlMapClientFactoryBean{

	//SIEV 13 JUL
	
	protected void applyTransactionConfig(SqlMapClient sqlMapClient, TransactionConfig transactionConfig) {
		if (!(sqlMapClient instanceof ExtendedSqlMapClient)) {
			throw new IllegalArgumentException(
					"Cannot set TransactionConfig with DataSource for SqlMapClient if not of type \n " +
					"ExtendedSqlMapClient: " + sqlMapClient);
		}
		ExtendedSqlMapClient extendedClient = (ExtendedSqlMapClient) sqlMapClient;
		transactionConfig.setMaximumConcurrentTransactions(extendedClient.getDelegate().getMaxTransactions());
		extendedClient.getDelegate().setTxManager(new TxManager(transactionConfig));
	}
	
	public class TxManager extends TransactionManager {
		private TransactionConfig transactionConfig;
		public TxManager(TransactionConfig transactionConfig) {
			super(transactionConfig);
			this.transactionConfig = transactionConfig;
		}
		
		public DataSource getDataSource() {
			DataSource dataSource = transactionConfig.getDataSource();
			if(dataSource instanceof TransactionAwareDataSourceProxy){
				if(((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource() instanceof RoutingDataSource){
					return ((RoutingDataSource)((TransactionAwareDataSourceProxy)dataSource).getTargetDataSource()).getDataSource();
				}
			}
			return transactionConfig.getDataSource();
		}
	}
}