package me.illia.jlog;

public class JLoggerStyle {
    protected String error;
    protected String warning;
    protected String critical;
    protected String criticalMsg;
    protected String info;

    public JLoggerStyle() {
        setInfo("\u001B[32m");
        setCritical("\u001B[31m", "\u001B[30m");
        setWarning("\u001B[33m");
        setError("\u001B[31m");
    }
    public JLoggerStyle(String error, String warning, String critical, String criticalMsg, String info) {
        setError(error);
        setWarning(warning);
        setCritical(critical, criticalMsg);
        setInfo(info);
    }

    public JLoggerStyle setError(String error) {
        this.error = error;
        return this;
    }
    public JLoggerStyle setWarning(String warning) {
        this.warning = warning;
        return this;
    }
    public JLoggerStyle setCritical(String critical, String criticalMsg) {
        this.critical = critical;
        this.criticalMsg = criticalMsg;
        return this;
    }
    public JLoggerStyle setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getError() {
        return error;
    }

    public String getWarning() {
        return warning;
    }

    public String getCritical() {
        return critical;
    }

    public String getCriticalMsg() {
        return criticalMsg;
    }

    public String getInfo() {
        return info;
    }
}
