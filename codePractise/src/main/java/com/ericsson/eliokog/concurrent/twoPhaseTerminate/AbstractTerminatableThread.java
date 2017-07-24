package com.ericsson.eliokog.concurrent.twoPhaseTerminate;

/**
 * Created by eliokog on 2017/7/19.
 */
public abstract class AbstractTerminatableThread extends Thread implements Terminatable {

    protected final TerminateToken terminateToken;

    public AbstractTerminatableThread(){
        this(new TerminateToken());
    }

    public AbstractTerminatableThread(TerminateToken terminateToken) {
        super();
        this.terminateToken = terminateToken;
        terminateToken.register(this);
    }

    @Override
    public void run(){
        Exception ex = null;
        try {
            for(;;){
                if(terminateToken.isShutdown()&&terminateToken.counts.get()<1){
                    break;
                }
                doRun();
            }
        }catch (Exception e){
            ex = e;
        }finally {
            try {
                doCleanup( ex);
            }finally{
                terminateToken.notifyThreadTermination(this);
            }
        }

    }

    @Override
    public void terminate() {
        terminateToken.setToShutdown();
        try {
            doTerminate();
        }finally {
            if (terminateToken.counts.get()<1){
                super.interrupt();
            }
        }
    }

    @Override
    public void interrupt(){
        terminate();
    }


    protected  void doTerminate(){}

    protected void doCleanup(Exception ex){}

    protected void doRun(){}
}
