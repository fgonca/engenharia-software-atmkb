package atmkb.dao;

public class ExcepcaoDAO extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ExcepcaoDAO(String mensagem)
	{
		super(mensagem);
	}
}
