package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 20/02/2015.
 */
public class QueryExceptionError extends DatabaseConnectionError {
    public QueryExceptionError(int errCode, Exception e){
        super(errCode, e);
    }

    public QueryExceptionError(int errCode){
        super(errCode);
    }
}
