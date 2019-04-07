module data {
    requires gson;
    requires xstream;
    requires org.junit.jupiter.api;

    opens utils to xstream;

    exports utils;
}
