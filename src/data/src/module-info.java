module data {
    requires gson;
    requires xstream;

    opens utils to xstream;

    exports utils;
}
