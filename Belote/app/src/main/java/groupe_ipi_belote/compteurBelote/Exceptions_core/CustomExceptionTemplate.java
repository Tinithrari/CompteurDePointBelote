package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 10/02/2015.
 */
public abstract class CustomExceptionTemplate extends Exception{

    protected String message;
    protected int errCode;

    public CustomExceptionTemplate(int errCode, Exception e){
        super(e);
        this.errCode = errCode;
    }

    public CustomExceptionTemplate(int errCode){
        this(errCode, null);
    }

    public String getMessage(){
        return message;
    }
    public int getErrCode(){ return errCode; }
}
