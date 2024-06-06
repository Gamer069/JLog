package me.illia.jlog;

import me.illia.jlog.other.JLogColor;

public class JLogStyle {
    protected JLogColor error;
    protected JLogColor warning;
    protected JLogColor critical;
    protected JLogColor info;
    protected JLogColor normal;

    public JLogStyle() {
        setInfo(JLogColor.GREEN).setCritical(JLogColor.DARK_RED).setWarning(JLogColor.YELLOW).setError(JLogColor.RED).setNormal(JLogColor.BLACK);
    }

    public JLogStyle(JLogColor error, JLogColor warning, JLogColor critical, JLogColor info, JLogColor normal) {
        setError(error).setWarning(warning).setCritical(critical).setInfo(info).setNormal(normal);
    }

    public JLogStyle setError(JLogColor error) {
        this.error = error;
        return this;
    }

    public JLogStyle setWarning(JLogColor warning) {
        this.warning = warning;
        return this;
    }

    public JLogStyle setCritical(JLogColor critical) {
        this.critical = critical;
        return this;
    }

    public JLogStyle setInfo(JLogColor info) {
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

    public JLogColor getNormal() {
        return normal;
    }

    public void setNormal(JLogColor normal) {
        this.normal = normal;
    }
}
