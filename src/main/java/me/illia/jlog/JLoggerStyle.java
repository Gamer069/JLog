package me.illia.jlog;

import me.illia.jlog.other.*;

public class JLoggerStyle {
    protected JLogColor error;
    protected JLogColor warning;
    protected JLogColor critical;
    protected JLogColor info;

    public JLoggerStyle() {
        setInfo(JLogColor.GREEN);
        setCritical(JLogColor.DARK_RED);
        setWarning(JLogColor.YELLOW);
        setError(JLogColor.RED);
    }
    public JLoggerStyle(JLogColor error, JLogColor warning, JLogColor critical, JLogColor info) {
        setError(error);
        setWarning(warning);
        setCritical(critical);
        setInfo(info);
    }

    public JLoggerStyle setError(JLogColor error) {
        this.error = error;
        return this;
    }
    public JLoggerStyle setWarning(JLogColor warning) {
        this.warning = warning;
        return this;
    }
    public JLoggerStyle setCritical(JLogColor critical) {
        this.critical = critical;
        return this;
    }
    public JLoggerStyle setInfo(JLogColor info) {
        this.info = info;
        return this;
    }

    public JLogColor getError() {
        return error;
    }

    public JLogColor getWarning() {
        return warning;
    }

    public JLogColor getCritical() {
        return critical;
    }

    public JLogColor getInfo() {
        return info;
    }
}
