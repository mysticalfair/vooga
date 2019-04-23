module gameengine {
    requires java.desktop;
    requires data;
    requires org.junit.jupiter.api;
    exports state.attribute;
    exports state.action;
    exports state.actiondecision;
    exports state.agent;

}