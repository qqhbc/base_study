package design;

/**
 * 责任链
 */

interface Test {
    void hello();
}

abstract class Handler {
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int ERROR = 3;

    private Handler nextHandler;
    protected int level;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void report(int level, String msg) {
        if (level < Handler.DEBUG) {
            setMsg(msg);
            return;
        }
        if (this.level == level) {
            setMsg(msg);
        } else {
            if (this.nextHandler != null)
                this.nextHandler.report(level, msg);
            else setMsg(msg); //大于级别统一 Error
        }
    }

    abstract protected void setMsg(String msg);
}

class DebugHandle extends Handler {

    public DebugHandle(int level) {
        this.level = level;
    }

    @Override
    protected void setMsg(String msg) {
        System.out.println(msg + " This a Debug level error");
    }
}

class InfoHandle extends Handler {

    public InfoHandle(int level) {
        this.level = level;
    }

    @Override
    protected void setMsg(String msg) {
        System.out.println(msg + " This a Info level error");
    }
}

class ErrorHandle extends Handler {

    public ErrorHandle(int level) {
        this.level = level;
    }

    @Override
    protected void setMsg(String msg) {
        System.out.println(msg + " This a Error level error");
    }
}

public class DutyChainDemo {
    private static Handler getChainOfHandler() {
        DebugHandle debugHandle = new DebugHandle(Handler.DEBUG);
        InfoHandle infoHandle = new InfoHandle(Handler.INFO);
        ErrorHandle errorHandle = new ErrorHandle(Handler.ERROR);

        debugHandle.setNextHandler(infoHandle);
        infoHandle.setNextHandler(errorHandle);

        return debugHandle;
    }

    public static void main(String[] args) {
        Handler handler = getChainOfHandler();
        handler.report(-1, "这个是那个等级呢！");
    }
}
