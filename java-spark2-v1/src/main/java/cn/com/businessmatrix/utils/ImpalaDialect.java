package cn.com.businessmatrix.utils;

import org.apache.spark.sql.jdbc.JdbcDialect;

public class ImpalaDialect extends JdbcDialect {
	
	static ImpalaDialect instance;
	
	static
	{
		instance=new ImpalaDialect();
	}

	public static JdbcDialect getInstance()
	{
		return instance;
	}
	private static final long serialVersionUID = 1L;
	@Override
    public boolean canHandle(String url) {
        return url.startsWith("jdbc:impala") || url.contains("impala");
    }
    @Override
    public String quoteIdentifier(String colName) {
        return colName;
    }

}
