module data {
    requires xstream;
    requires org.junit.jupiter.api;
    requires kxml2;

    opens utils to xstream;

    exports utils;
}
